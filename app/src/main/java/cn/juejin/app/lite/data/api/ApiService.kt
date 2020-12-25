package cn.juejin.app.lite.data.api

import cn.juejin.app.lite.data.response.ApiResponse
import cn.juejin.app.lite.data.response.RecommendListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    /**
     * 首页推荐
     */
    //{"id_type":2,"client_type":2608,"sort_type":200,"cursor":"0","limit":20}
    @POST("recommend_api/v1/article/recommend_all_feed")
    @JvmSuppressWildcards
    suspend fun getRecommendAllFeedList(@Body params: Map<String, Any>): ApiResponse<List<RecommendListResponse>>

    /**
     * 获取每个category下的tag
     */
    //{"cate_id":"6809637769959178254"}
    @POST("recommend_api/v1/tag/recommend_tag_list")
    @JvmSuppressWildcards
    fun getRecommendTagList(@Body params: Map<String, Any>)

    /**
     * 获取每个category下的文章列表
     */
    //{"id_type":2,"sort_type":200,"cate_id":"6809637769959178254","cursor":"0","limit":20}
    @POST("recommend_api/v1/article/recommend_cate_feed")
    @JvmSuppressWildcards
    fun getCategoryFeedList(@Body params: Map<String, Any>)

    /**
     * 文章详情页下面的相关推荐列表
     */
    //{"id_type":2,"cursor":"0","item_id":"6906279483448393735","tag_ids":["6809640445233070094","6809641037787561992"],"sort_type":200}
    @POST("recommend_api/v1/article/recommend_tag_feed")
    @JvmSuppressWildcards
    fun getRecommendTagFeedList(@Body params: Map<String, Any>)

    /**
     * 文章详情页中的相关文章列表
     */
    //{"id_type":2,"tag_ids":["6809640445233070094","6809641037787561992"],"user_id":"958429871749192","item_id":"6906279483448393735","cursor":"0"}
    @POST("recommend_api/v1/article/recommend_article_detail_feed")
    @JvmSuppressWildcards
    fun getRecommendArticleDetailFeedList(@Body params: Map<String, Any>)

    /**
     * 获取文章详情
     */
    //{"article_id":"6906279483448393735"}
    @POST("content_api/v1/article/detail")
    @JvmSuppressWildcards
    fun getArticleDetail(@Body params: Map<String, Any>)

    /**
     * 获取评论列表
     */
    //{"item_id":"6906279483448393735","item_type":2,"cursor":"0","limit":20,"client_type":2608}
    @JvmSuppressWildcards
    @POST("interact_api/v1/comment/list")
    fun getCommentList(@Body params: Map<String, Any>)
}