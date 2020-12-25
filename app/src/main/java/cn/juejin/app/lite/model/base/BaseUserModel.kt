package cn.juejin.app.lite.model.base

open class BaseUserModel {

    var userId: String = ""
    var username: String = ""
    var avatar: String = ""
    var company: String = ""
    var jobTitle: String = ""
    var level: Int = 0
    var description: String = ""
    var power: Long = 0
    var follow: Long = 0
    var followers: Long = 0
    var postArticleCount: Long = 0
    var praiseArticleCount: Long = 0
    var praiseTotalCount: Long = 0
    var viewTotalCount: Long = 0
    var isFollow: Boolean = false
}