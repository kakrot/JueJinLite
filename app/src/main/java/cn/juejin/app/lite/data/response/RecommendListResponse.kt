package cn.juejin.app.lite.data.response

data class RecommendListResponse(
    var item_type: Int = 0,
    var item_info: FeedInfoResponse = FeedInfoResponse()
)
