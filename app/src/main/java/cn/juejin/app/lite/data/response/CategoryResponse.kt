package cn.juejin.app.lite.data.response

/**
 * {
"category_id": "6809637767543259144",
"category_name": "前端",
"category_url": "frontend",
"rank": 2,
"ctime": 1457483942,
"mtime": 1432503190,
"show_type": 3
}
 */
data class CategoryResponse(
    var category_id: String = "",
    var category_name: String = "",
    var category_url: String = "",
    var rank: Int = 0,
    var show_type: Int = 0
)