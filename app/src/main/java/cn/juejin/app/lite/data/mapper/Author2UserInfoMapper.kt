package cn.juejin.app.lite.data.mapper

import cn.juejin.app.lite.data.response.AuthorUserInfoResponse
import cn.juejin.app.lite.model.base.BaseUserModel

class Author2UserInfoMapper : Mapper<AuthorUserInfoResponse, BaseUserModel> {

    override fun map(source: AuthorUserInfoResponse): BaseUserModel {
        val model = BaseUserModel()
        model.userId = source.user_id
        model.username = source.user_name
        model.avatar = source.avatar_large
        return model
    }
}