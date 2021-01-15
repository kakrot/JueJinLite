package cn.juejin.app.lite.module.detail

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import cn.juejin.app.lite.app.launch
import cn.juejin.app.lite.common.AbstractViewModel
import cn.juejin.app.lite.data.mapper.FeedInfo2ArticleMapper
import cn.juejin.app.lite.data.repository.Repository
import cn.juejin.app.lite.model.Article

class ArticleInfoViewModel(application: Application) : AbstractViewModel(application) {

    val article = MutableLiveData<Article>()
    private val articleMapper = FeedInfo2ArticleMapper()
    var articleId = ""

    override fun onRestoreBundle(args: Bundle?) {
        articleId = args?.getString("articleId", "") ?: ""
    }

    override fun onSaveBundle(args: Bundle) {
        args.putString("articleId", articleId)
    }

    fun getArticleDetail() {
        launch(
            { Repository.getArticleDetail("6914943416128241678") },
            {
                if (it.isSuccessful() && null != it.data) {
                    val result = articleMapper.map(it.data!!)
                    article.value = result
                }
            }
        )
    }

}