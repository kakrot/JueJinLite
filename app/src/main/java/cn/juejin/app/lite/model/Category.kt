package cn.juejin.app.lite.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var id: String = "",
    var name: String = "",
    var url: String = "",
    var rank: Int = 0
) : Parcelable