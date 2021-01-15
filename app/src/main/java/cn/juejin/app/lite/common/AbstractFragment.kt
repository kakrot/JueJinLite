package cn.juejin.app.lite.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class AbstractFragment : Fragment() {

    abstract fun getLayoutId(): Int

    abstract fun initView(view: View)

    abstract fun initData(savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData(savedInstanceState)
    }

    protected fun <T : ViewModel> fragmentViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(this)[modelClass]
    }

    protected fun <T : ViewModel> activityViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(requireActivity())[modelClass]
    }
}