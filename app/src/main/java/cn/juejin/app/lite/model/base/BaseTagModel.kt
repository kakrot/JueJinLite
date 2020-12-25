package cn.juejin.app.lite.model.base

open class BaseTagModel {
    var id: Long = 0
    var tagId: String = ""
    var tagName: String = ""
    var color: String = ""
    var icon: String = ""
    var background: String = ""
    var showNav: Boolean = false
    var article: Long = 0
    var follow: Long = 0
}