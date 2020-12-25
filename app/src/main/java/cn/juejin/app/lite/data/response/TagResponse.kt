package cn.juejin.app.lite.data.response

/**
 * {
"id": 2546526,
"tag_id": "6809640407484334093",
"tag_name": "前端",
"color": "#60ADFF",
"icon": "https://lc-gold-cdn.xitu.io/bac28828a49181c34110.png",
"back_ground": "",
"show_navi": 1,
"tag_alias": "",
"post_article_count": 49369,
"concern_user_count": 482841
}
 */
data class TagResponse(

    var id:Long = 0,
    var tag_id:String = "",
    var tag_name:String=""

)
