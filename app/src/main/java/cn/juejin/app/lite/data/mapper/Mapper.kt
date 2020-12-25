package cn.juejin.app.lite.data.mapper

interface Mapper<I, O> {

    fun map(source: I, result: O)

}