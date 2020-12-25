package cn.juejin.app.lite.data.response

/**
 * {
"user_id": "2330620381629070",
"user_name": "lzg9527",
"company": "",
"job_title": "前端开发工程师 | 广州",
"avatar_large": "https://user-gold-cdn.xitu.io/2020/3/22/17101b796c4491e0?w=180\u0026h=180\u0026f=jpeg\u0026s=6687",
"level": 4,
"description": "",
"followee_count": 35,
"follower_count": 5059,
"post_article_count": 44,
"digg_article_count": 85,
"got_digg_count": 5958,
"got_view_count": 233076,
"post_shortmsg_count": 5,
"digg_shortmsg_count": 1,
"isfollowed": false,
"favorable_author": 1,
"power": 8288,
"study_point": 0,
"university": {
"university_id": "0",
"name": "",
"logo": ""
},
"major": {
"major_id": "0",
"parent_id": "0",
"name": ""
},
"student_status": 0,
"select_event_count": 0,
"select_online_course_count": 0,
"identity": 0
}
 */
data class AuthorUserInfoResponse(
    var user_id: String = "",
    var user_name: String = "",
    var company: String = "",
    var job_title: String = "",
    var avatar_large: String = "",
    var level: Int = 0,
    var description: String = "",
    var followee_count: Long = 0,
    var follower_count: Long = 0,
    var post_article_count: Long = 0,
    var digg_article_count: Long = 0,
    var got_digg_count: Long = 0,
    var got_view_count: Long = 0,
    var post_shortmsg_count: Long = 0,
    var digg_shortmsg_count: Long = 0,
    var isfollowed: Boolean = false,
    var favorable_author: Int = 0,
    var power: Long = 0
)
