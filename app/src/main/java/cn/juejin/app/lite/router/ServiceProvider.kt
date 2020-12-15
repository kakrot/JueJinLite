package cn.juejin.app.lite.router

import androidx.collection.ArrayMap
import java.util.*

object ServiceProvider {

    private val services: ArrayMap<String, Any?> = ArrayMap()

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

inline fun <reified T> service(): T? {
    return ServiceProvider.getService(T::class.java)
}