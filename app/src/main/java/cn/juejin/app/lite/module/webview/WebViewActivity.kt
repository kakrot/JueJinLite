package cn.juejin.app.lite.module.webview

import cn.juejin.app.lite.common.AbstractActivity

class WebViewActivity : AbstractActivity() {

    private lateinit var webExplorerView: WebExplorerView

    override fun initView() {
        webExplorerView = WebExplorerView(this)
        setContentView(webExplorerView)
        webExplorerView.setLifecycle(this)
    }

    override fun initData() {
        val articleId = intent.getStringExtra("articleId")
        webExplorerView.loadUrl("https://juejin.cn/post/${articleId}")
    }

}