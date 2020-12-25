package cn.juejin.app.lite.data.response

/**
 * {
"id": 6906028995133833230,
"omitempty": 2,
"user_id": 2277843825068702,
"is_digg": false,
"is_follow": false,
"is_collect": false
}
 */
data class UserInteractResponse(
    var id: String = "",
    var user_id: String = "",
    var is_digg: Boolean = false,
    var is_follow: Boolean = false,
    var is_collect: Boolean = false
)
