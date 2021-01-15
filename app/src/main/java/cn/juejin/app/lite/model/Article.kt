package cn.juejin.app.lite.model

import cn.juejin.app.lite.model.base.BaseFeedModel
import cn.juejin.app.lite.model.base.BaseTagModel
import cn.juejin.app.lite.model.base.BaseUserModel

class Article : BaseFeedModel() {
    var category = Category()
    var user = BaseUserModel()
    var tags = mutableListOf<BaseTagModel>()
    var relation = RelationModel()
}