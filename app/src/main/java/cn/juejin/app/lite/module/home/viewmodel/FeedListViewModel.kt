package cn.juejin.app.lite.module.home.viewmodel

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import cn.juejin.app.lite.app.launch
import cn.juejin.app.lite.common.AbstractViewModel
import cn.juejin.app.lite.data.mapper.FeedInfo2ArticleMapper
import cn.juejin.app.lite.data.repository.Repository
import cn.juejin.app.lite.model.Article

class FeedListViewModel(application: Application) : AbstractViewModel(application) {

    val dataSet = MutableLiveData<List<Article>>()
    val isLoading = MutableLiveData<Boolean>()
    val hasMore = MutableLiveData<Boolean>()
    private var cursor = "0"
    var categoryId = ""
    private val mapper = FeedInfo2ArticleMapper()

    override fun onRestoreBundle(args: Bundle?) {
        categoryId = args?.getString("categoryId", "") ?: ""
    }

    override fun onSaveBundle(args: Bundle) {
        args.putString("categoryId", categoryId)
    }

    fun onRefresh() {
        cursor = "0"
        doRequest()
    }

    fun onLoadMore() {
        doRequest()
    }

    private fun getRecommendAllFeedList() {
        launch(
            {
                Repository.getRecommendAllFeedList(
                    mapOf(
                        "id_type" to 2,
                        "sort_type" to 200,
                        "cursor" to cursor,
                        "limit" to 20,
                        "client_type" to 2608
                    )
                )
            },
            {
                if (it.isSuccessful()) {
                    val data = it.data
                    if (!data.isNullOrEmpty()) {
                        val list = arrayListOf<Article>()
                        data.forEach { res ->
                            if (res.isArticle()) {
                                list.add(mapper.map(res.item_info))
                            }
                        }
                        dataSet.value = list
                    }
                    hasMore.value = it.hasMore
                    cursor = it.cursor
                }
            }
        )
    }

    private fun getCategoryFeedList() {
        launch({
            Repository.getCategoryFeedList(
                mapOf(
                    "id_type" to 2,
                    "cate_id" to categoryId,
                    "sort_type" to 200,
                    "cursor" to cursor,
                    "limit" to 20
                )
            )
        }, {
            if (it.isSuccessful()) {
                val data = it.data
                if (!data.isNullOrEmpty()) {
                    val list = data.map { item -> mapper.map(item) }
                    dataSet.value = list
                }
                hasMore.value = it.hasMore
                cursor = it.cursor
            }
        })
    }

    private fun doRequest() {
        if (TextUtils.isEmpty(categoryId)) {
            getRecommendAllFeedList()
        } else {
            //"6809635626879549454"
            getCategoryFeedList()
        }
    }

}