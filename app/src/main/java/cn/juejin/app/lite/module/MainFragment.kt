package cn.juejin.app.lite.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.juejin.app.lite.common.BaseFragment
import cn.juejin.app.lite.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == binding) {
            binding = FragmentMainBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

}