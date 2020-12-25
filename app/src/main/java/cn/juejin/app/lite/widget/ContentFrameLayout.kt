package cn.juejin.app.lite.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import cn.juejin.app.lite.R

class ContentFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var emptyDataLayoutId: Int = 0
    private var networkUnableLayoutId: Int = 0
    private var requestErrorView: View? = null

    init {
        val array = context.obtainStyledAttributes(R.styleable.ContentFrameLayout)
        val requestErrorLayoutId = array.getResourceId(
            R.styleable.ContentFrameLayout_request_error_layout,
            0
        )
        requestErrorView = ViewStub(context).apply { inflatedId = requestErrorLayoutId }
        emptyDataLayoutId = array.getResourceId(
            R.styleable.ContentFrameLayout_empty_data_layout,
            0
        )
        networkUnableLayoutId =
            array.getResourceId(
                R.styleable.ContentFrameLayout_network_unable_layout,
                0
            )
        array.recycle()
    }

    fun showRequestError() {

    }

    fun showEmptyData() {

    }

    fun showNetworkUnable() {

    }

    fun showContent() {

    }
}