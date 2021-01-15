package cn.juejin.app.lite.module.home.fragment

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import cn.juejin.app.lite.R
import cn.juejin.app.lite.common.AbstractFragment
import cn.juejin.app.lite.module.home.adapter.HomePageAdapter
import cn.juejin.app.lite.module.home.viewmodel.HomePageViewModel
import com.google.android.material.tabs.TabLayout

class HomePageFragment : AbstractFragment() {

    private lateinit var adapter: HomePageAdapter
    private lateinit var viewModel: HomePageViewModel
    private lateinit var viewpager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_page
    }

    override fun initView(view: View) {
        viewpager = view.findViewById(R.id.home_vp_page)
        tabLayout = view.findViewById(R.id.home_tl_tab)
        adapter = HomePageAdapter(this)
        viewpager.adapter = adapter
        tabLayout.setupWithViewPager(viewpager)
    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel = fragmentViewModel(HomePageViewModel::class.java)
        viewModel.categoryList.observe(viewLifecycleOwner, { adapter.refreshData(it) })
        viewModel.reqCategoryList()
    }


}