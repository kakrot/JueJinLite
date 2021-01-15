package cn.juejin.app.lite.data.mapper

import cn.juejin.app.lite.data.response.ArticleInfoResponse
import cn.juejin.app.lite.model.Article

class ArticleMapper : Mapper<ArticleInfoResponse, Article> {

    override fun map(source: ArticleInfoResponse): Article {
        val article = Article()
        article.id = source.article_id
        article.title = source.title
        article.content = source.brief_content
        article.cover = source.cover_image
        article.viewCount = source.view_count
        article.collectCount = source.collect_count
        article.commentCount = source.comment_count
        article.praiseCount = source.digg_count
        return article
    }
}