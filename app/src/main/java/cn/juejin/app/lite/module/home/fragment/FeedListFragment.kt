package cn.juejin.app.lite.module.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.juejin.app.lite.R
import cn.juejin.app.lite.common.AbstractFragment
import cn.juejin.app.lite.model.Article
import cn.juejin.app.lite.module.home.adapter.FeedListAdapter
import cn.juejin.app.lite.module.home.viewmodel.FeedListViewModel

class FeedListFragment : AbstractFragment() {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: FeedListViewModel
    private lateinit var adapter: FeedListAdapter

    override fun getLayoutId(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FeedListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        refreshLayout = SwipeRefreshLayout(requireContext())
        recyclerView = RecyclerView(requireContext())
        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        refreshLayout.layoutParams = lp
        refreshLayout.addView(recyclerView, lp)
        return refreshLayout
    }

    @CallSuper
    override fun initView(view: View) {
        refreshLayout.setOnRefreshListener { onRefresh() }
        adapter.onLoadMore = { onLoadMore() }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.addItemDecoration(getItemDecoration())
    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        viewModel = fragmentViewModel(FeedListViewModel::class.java)
        viewModel.onRestoreBundle(savedInstanceState ?: arguments)
        viewModel.isLoading.observe(viewLifecycleOwner, { onLoadStateChanged(it) })
        viewModel.dataSet.observe(viewLifecycleOwner, { onDataReceived(it) })
        viewModel.hasMore.observe(viewLifecycleOwner, { adapter.canLoadMore = it })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.onSaveBundle(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        if (adapter.itemCount == 0) {
            onRefresh()
        }
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    private fun getItemDecoration(): RecyclerView.ItemDecoration {
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val drawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.recycler_divider_shape)
        if (null != drawable) {
            itemDecoration.setDrawable(drawable)
        }
        return itemDecoration
    }

    private fun onDataReceived(data: List<Article>) {
        refreshLayout.isRefreshing = false
        adapter.submitList(data)
    }

    private fun onLoadStateChanged(state: Boolean) {
        refreshLayout.isRefreshing = state
    }

    private fun onRefresh() {
        viewModel.onRefresh()
    }

    private fun onLoadMore() {
        viewModel.onLoadMore()
    }
}