package cn.juejin.app.lite.module.home.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import cn.juejin.app.lite.model.Category
import cn.juejin.app.lite.module.home.fragment.FeedListFragment

class HomePageAdapter(private val fragment: Fragment) : FragmentStatePagerAdapter(
    fragment.childFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val models = arrayListOf<Category>()

    fun refreshData(elements: List<Category>) {
        if (elements.isEmpty()) {
            return
        }
        if (models.isNotEmpty()){
            models.clear()
        }
        models.addAll(elements)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return models.size
    }

    override fun getItem(position: Int): Fragment {
        val classLoader = fragment.requireContext().classLoader
        val ff = fragment.childFragmentManager.fragmentFactory
        val child = ff.instantiate(classLoader, FeedListFragment::class.java.name)
        child.arguments = bundleOf("categoryId" to models[position].id)
        return child
    }

    override fun getPageTitle(position: Int): CharSequence {
        return models[position].name
    }
}