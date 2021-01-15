package cn.juejin.app.lite.data.mapper

import cn.juejin.app.lite.data.response.FeedInfoResponse
import cn.juejin.app.lite.model.Article

class FeedInfo2ArticleMapper : Mapper<FeedInfoResponse, Article> {

    private val articleMapper = ArticleMapper()
    private val categoryMapper = CategoryMapper()
    private val author2UserInfoMapper = Author2UserInfoMapper()

    override fun map(source: FeedInfoResponse): Article {
        val article = articleMapper.map(source.article_info)
        article.category = categoryMapper.map(source.category)
        article.user = author2UserInfoMapper.map(source.author_user_info)
        return article
    }


}