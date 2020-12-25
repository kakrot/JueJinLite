package cn.juejin.app.lite.data.response

data class FeedInfoResponse(
    var article_id: String = "",
    var article_info: ArticleInfoResponse = ArticleInfoResponse(),
    var author_user_info: AuthorUserInfoResponse = AuthorUserInfoResponse(),
    var category: CategoryResponse = CategoryResponse(),
    var tags: List<TagResponse> = arrayListOf(),
    var user_interact: UserInteractResponse = UserInteractResponse()
)