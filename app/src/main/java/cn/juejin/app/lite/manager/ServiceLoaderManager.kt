package cn.juejin.app.lite.manager

import androidx.collection.ArrayMap
import java.util.*

object ServiceLoaderManager {

    private val services: ArrayMap<String, Any?> = ArrayMap()

    @Suppress("UNCHECKED_CAST")
    fun <T> getService(clazz: Class<T>): T? {
        var value = services[clazz.name]
        if (null == value) {
            val iterator = ServiceLoader.load(clazz).iterator()
            if (iterator.hasNext()) {
                value = iterator.next()
                services[clazz.name] = value
            }
        }
        return if (null == value) null else (value as T)
    }

}