package cn.juejin.app.lite.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class ListFragment<M, VM : ListViewModel<M>> : BaseFragment() {

    protected val refreshLayout by lazy { SwipeRefreshLayout(requireContext()) }
    protected val recyclerView by lazy { RecyclerView(requireContext()) }
    protected val viewModel by lazy { createViewModel() }

    abstract fun createViewModel(): VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == recyclerView.parent) {
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            refreshLayout.layoutParams = lp
            refreshLayout.addView(recyclerView, lp)
        }
        return refreshLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        refreshLayout.setOnRefreshListener { onRefresh() }
    }

    private fun initViewModel() {
        viewModel.isLoading.observe(
            viewLifecycleOwner,
            Observer<Boolean> { onLoadStateChanged(it) })
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