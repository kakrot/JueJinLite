package cn.juejin.app.lite.data.mapper

import cn.juejin.app.lite.data.response.CategoryResponse
import cn.juejin.app.lite.model.Category

class CategoryMapper : Mapper<CategoryResponse, Category> {

    override fun map(source: CategoryResponse): Category {
        return Category(source.category_id, source.category_name, source.category_url, source.rank)
    }
}