package cn.juejin.app.lite.module.main

import androidx.activity.viewModels
import cn.juejin.app.lite.common.AbstractActivity

class MainActivity : AbstractActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun initView() {
        val fm = supportFragmentManager
        val fragment = fm.fragmentFactory.instantiate(classLoader, MainFragment::class.java.name)
        fm.beginTransaction()
            .replace(android.R.id.content, fragment)
            .commitNowAllowingStateLoss()
    }

    override fun initData() {

    }
}