package cn.juejin.app.lite.manager

import androidx.collection.ArrayMap

object HttpManager {

    private val classCache = ArrayMap<String, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T> create(clazz: Class<T>): T {
        val className = clazz.name
        var service = classCache[className]
        if (null == service) {
            service = AppModule.providerRetrofit().create(clazz)
            classCache[className] = service
        }
        return service as T
    }
}
