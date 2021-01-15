package cn.juejin.app.lite.module.webview

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.tencent.smtt.export.external.interfaces.*
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import java.io.ByteArrayInputStream
import java.lang.ref.WeakReference

class WebExplorerView(context: Context) : FrameLayout(context), LifecycleEventObserver {

    private var hasObserver = false
    private var webview: WebView? = null

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        webview = createWebView()
    }

    fun loadUrl(url: String) {
        webview?.loadUrl(url)
    }

    fun setLifecycle(owner: LifecycleOwner) {
        if (!hasObserver) {
            owner.lifecycle.addObserver(this)
            hasObserver = true
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                source.lifecycle.removeObserver(this)
                destroyWebView()
            }
            Lifecycle.Event.ON_RESUME -> {
                webview?.resumeTimers()
                webview?.onResume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                webview?.pauseTimers()
                webview?.onPause()
            }
            else -> {
            }
        }
    }

    private fun getRemoveElementJs(elementName: String): String {
        return "document.getElementsByClassName(\"$elementName\")[0].parentNode.removeChild(document.getElementsByClassName(\"$elementName\")[0]);"
    }

    private fun removeElements() {
        val js = "javascript:function removeElements(){" +
                getRemoveElementJs("main-header-box") +
                getRemoveElementJs("author-info-block") +
                getRemoveElementJs("action-bar") +
                getRemoveElementJs("tag-list-box") +
                getRemoveElementJs("footer-author-block") +
                getRemoveElementJs("article-banner") +
                getRemoveElementJs("comment-list-box") +
                getRemoveElementJs("recommended-area") +
                "}"
        webview?.let {
            it.loadUrl(js)
            it.loadUrl("javascript:removeElements();")
        }
    }

    private fun createWebView(): WebView {
        if (null == webview) {
            val view = this
            webview = WebView(context).apply {
                webViewClient = WebViewClientImpl(view)
                webChromeClient = WebChromeClientImpl()
                settings.apply {
                    // 支持Js使用
                    javaScriptEnabled = true
                    // 开启DOM缓存
                    domStorageEnabled = true
                    // 开启数据库缓存
                    databaseEnabled = true
                    // 设置 WebView 的缓存模式
                    cacheMode = WebSettings.LOAD_DEFAULT
                    // 支持启用缓存模式
                    setAppCacheEnabled(true)
                    // 支持缩放
                    setSupportZoom(true)
                }
            }
            addView(webview, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        return webview!!
    }

    private fun destroyWebView() {
        if (null == webview) {
            return
        }
        webview?.let {
            removeView(it)
            it.clearCache(false)
            it.destroy()
        }
        webview = null
    }

    // 支持自动加载图片
    private fun setLoadsImagesAutomatically(enable: Boolean) {
        webview?.settings?.loadsImagesAutomatically = enable
    }

    private fun onPageStarted(url: String?, favicon: Bitmap?) {
        setLoadsImagesAutomatically(false)
    }

    private fun onPageFinished(url: String?) {
        setLoadsImagesAutomatically(true)
        removeElements()
    }

    private class WebViewClientImpl(view: WebExplorerView) : WebViewClient() {
        private val viewRef = WeakReference(view)

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            viewRef.get()?.onPageStarted(url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            viewRef.get()?.onPageFinished(url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
            Log.d("WebExplorerView", "onLoadResource=>" + url.toString())
        }

        override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
            Log.d("WebExplorerView", "shouldInterceptRequest=>" + url.toString())
            var response: WebResourceResponse? = null
            if (url.toString().contains("juejin")) {
                if (url.toString().contains("recommend_tag_feed")) {
                    response = createEmptyWebResourceResponse()
                }
            } else {
                response = createEmptyWebResourceResponse()
            }
            return response
        }

        private fun createEmptyWebResourceResponse(): WebResourceResponse {
            return WebResourceResponse().apply {
                data =
                    ByteArrayInputStream("{\"err_no\":0,\"err_msg\":\"success\",\"data\":[],\"cursor\":\"0\",\"count\":0,\"has_more\":false}".toByteArray())
                setStatusCodeAndReasonPhrase(200, "")
            }
        }

        override fun onReceivedSslError(
            view: WebView?,
            handle: SslErrorHandler?,
            error: SslError?
        ) {
            handle?.proceed()
        }
    }

    private class WebChromeClientImpl : WebChromeClient() {

        override fun onJsPrompt(
            p0: WebView?,
            p1: String?,
            p2: String?,
            p3: String?,
            p4: JsPromptResult?
        ): Boolean {
            Log.d("WebExplorerView", "onJsPrompt=>")
            return false
        }

        override fun onJsAlert(p0: WebView?, p1: String?, p2: String?, p3: JsResult?): Boolean {
            Log.d("WebExplorerView", "onJsAlert=>")
            return false
        }

        override fun onJsConfirm(p0: WebView?, p1: String?, p2: String?, p3: JsResult?): Boolean {
            Log.d("WebExplorerView", "onJsConfirm=>")
            return false
        }

        override fun onConsoleMessage(p0: ConsoleMessage?): Boolean {
            Log.d("WebExplorerView", "onConsoleMessage=>")
            return false
        }

        override fun onShowCustomView(p0: View?, p1: IX5WebChromeClient.CustomViewCallback?) {
            Log.d("WebExplorerView", "onShowCustomView=>")
        }
    }
}