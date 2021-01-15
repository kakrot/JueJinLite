package cn.juejin.app.lite.module.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import cn.juejin.app.lite.R
import cn.juejin.app.lite.common.AbstractFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : AbstractFragment(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewpager: ViewPager
    private lateinit var navigation: BottomNavigationView

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(view: View) {
        viewpager = view.findViewById(R.id.main_viewpager)
        navigation = view.findViewById(R.id.main_bottom_navigation)
        val adapter = MainTabPagedListAdapter(requireContext(), childFragmentManager)
        viewpager.adapter = adapter
        viewpager.addOnPageChangeListener(this)
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        val itemId = when (position) {
            1 -> R.id.action_hot_point
            2 -> R.id.action_message_center
            3 -> R.id.action_profile
            else -> R.id.action_home
        }
        val selectedItemId = navigation.selectedItemId
        if (selectedItemId != itemId) {
            navigation.selectedItemId = itemId
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onDestroyView() {
        viewpager.removeOnPageChangeListener(this)
        super.onDestroyView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = when (item.itemId) {
            R.id.action_hot_point -> 1
            R.id.action_message_center -> 2
            R.id.action_profile -> 3
            else -> 0
        }
        val currentItem = viewpager.currentItem
        if (currentItem != position) {
            viewpager.currentItem = position
        }
        return true
    }

}