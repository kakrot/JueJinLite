package cn.juejin.app.lite.module

import android.app.Application
import android.util.Log
import cn.juejin.app.lite.app.launch
import cn.juejin.app.lite.common.BaseViewModel
import cn.juejin.app.lite.data.repository.Repository as response

class MainViewModel(application: Application) : BaseViewModel(application) {

    fun getRecommendAllFeedList() {
        //{"id_type":2,"client_type":2608,"sort_type":200,"cursor":"0","limit":20}
        val params = mapOf(
            "id_type" to 2,
            "client_type" to 2608,
            "sort_type" to 200,
            "cursor" to "0",
            "limit" to 20
        )
        launch(
            { response.getRecommendAllFeedList(params) },
            { Log.d("MainViewModel", it.toString()) }
        )
    }

}