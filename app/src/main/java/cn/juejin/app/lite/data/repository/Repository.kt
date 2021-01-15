package cn.juejin.app.lite.data.repository

import cn.juejin.app.lite.data.api.ApiService
import cn.juejin.app.lite.data.response.ApiResponse
import cn.juejin.app.lite.data.response.FeedInfoResponse
import cn.juejin.app.lite.data.response.RecommendListResponse
import cn.juejin.app.lite.manager.HttpManager
import retrofit2.http.Body

object Repository {

    suspend fun getRecommendAllFeedList(params: Map<String, Any>): ApiResponse<List<RecommendListResponse>> {
        return HttpManager.create(ApiService::class.java).getRecommendAllFeedList(params)
    }

    suspend fun getCategoryFeedList(params: Map<String, Any>): ApiResponse<List<FeedInfoResponse>> {
        return HttpManager.create(ApiService::class.java).getCategoryFeedList(params)
    }

    suspend fun getArticleDetail(articleId: String): ApiResponse<FeedInfoResponse> {
        return HttpManager.create(ApiService::class.java)
            .getArticleDetail(mapOf<String, Any>("article_id" to articleId))
    }
}