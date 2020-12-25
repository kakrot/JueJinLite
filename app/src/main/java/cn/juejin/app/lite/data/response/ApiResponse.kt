package cn.juejin.app.lite.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("err_no") var no: Int = 0,
    @SerializedName("err_msg") var msg: String = "",
    @SerializedName("data") var data: T? = null,
    @SerializedName("cursor") var cursor: String = "",
    @SerializedName("count") var count: Int = 0,
    @SerializedName("has_more") var hasMore: Boolean = false
) {
    fun isSuccessful() = no == 0
}