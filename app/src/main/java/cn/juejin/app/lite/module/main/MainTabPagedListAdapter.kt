package cn.juejin.app.lite.module.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.juejin.app.lite.module.home.fragment.HomePageFragment
import cn.juejin.app.lite.module.hotpoint.HotPointFragment
import cn.juejin.app.lite.module.messagecenter.MessageCenterFragment
import cn.juejin.app.lite.module.profile.ProfileFragment

class MainTabPagedListAdapter(
    private val ctx: Context,
    private val fm: FragmentManager
) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val models = arrayOf(
        HomePageFragment::class.java.name,
        HotPointFragment::class.java.name,
        MessageCenterFragment::class.java.name,
        ProfileFragment::class.java.name
    )

    override fun getItem(position: Int): Fragment {
        return fm.fragmentFactory.instantiate(ctx.classLoader, models[position])
    }

    override fun getCount(): Int {
        return models.size
    }
}