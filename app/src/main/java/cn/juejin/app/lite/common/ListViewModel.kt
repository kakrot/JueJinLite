package cn.juejin.app.lite.common

import android.app.Application
import androidx.lifecycle.MutableLiveData

abstract class ListViewModel<T>(application: Application) : BaseViewModel(application) {

    val dataSet = MutableLiveData<List<T>>()
    val isLoading = MutableLiveData<Boolean>()

    abstract fun onRefresh()

    abstract fun onLoadMore()

}