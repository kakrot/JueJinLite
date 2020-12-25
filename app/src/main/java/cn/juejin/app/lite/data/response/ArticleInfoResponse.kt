package cn.juejin.app.lite.data.response

/**
 * {
"article_id": "6906028995133833230",
"user_id": "2330620381629070",
"category_id": "6809637767543259144",
"tag_ids": [
6809640407484334093,
6809640369764958215
],
"visible_level": 0,
"link_url": "",
"cover_image": "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a877948e687b4132bddf7592da73846d~tplv-k3u1fbpfcp-watermark.image",
"is_gfw": 0,
"title": "分享8个非常实用的Vue自定义指令",
"brief_content": "在 Vue，除了核心功能默认内置的指令 ( v-model 和 v-show )，Vue 也允许注册自定义指令。它的作用价值在于当开发人员在某些场景下需要对普通 DOM 元素进行操作。 Vue 自定义指令有全局注册和局部注册两种方式。先来看看注册全局指令的方式，通过 Vue.d…",
"is_english": 0,
"is_original": 1,
"user_index": 0,
"original_type": 0,
"original_author": "",
"content": "",
"ctime": "1607935262",
"mtime": "1607990599",
"rtime": "1607935265",
"draft_id": "6906028317783916557",
"view_count": 4236,
"collect_count": 0,
"digg_count": 362,
"comment_count": 28,
"hot_index": 577,
"is_hot": 0,
"rank_index": 7.04773904,
"status": 2,
"verify_status": 1,
"audit_status": 2,
"mark_content": ""
}
 */
data class ArticleInfoResponse(

    var article_id: String = "",
    var user_id: String = "",
    var category_id: String = "",
    var link_url: String = "",
    var cover_image: String = "",
    var title: String = "",
    var brief_content: String = "",
    var content: String = "",
    var draft_id: String = "",
    var view_count: Long = 0,
    var collect_count: Long = 0,
    var digg_count: Long = 0,
    var comment_count: Long = 0,
    var hot_index: Long = 0,
    var is_hot: Int = 0,
    var rank_index: Double = 0.00,
    var status: Int = 0

)