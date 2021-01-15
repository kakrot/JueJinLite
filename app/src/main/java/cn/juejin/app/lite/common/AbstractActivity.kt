package cn.juejin.app.lite.common

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class AbstractActivity : AppCompatActivity() {

    abstract fun initView()

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = findViewById<View>(android.R.id.content)
        val controller = ViewCompat.getWindowInsetsController(content)
        controller?.isAppearanceLightStatusBars = true
        initView()
        initData()
    }

    protected fun <T : ViewModel> viewModel(modelClass: Class<T>):T {
        return ViewModelProvider(this)[modelClass]
    }

}