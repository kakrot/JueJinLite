package cn.juejin.app.lite.module.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import cn.juejin.app.lite.common.AbstractViewModel
import cn.juejin.app.lite.model.Category

class HomePageViewModel(application: Application) : AbstractViewModel(application) {

    val categoryList = MutableLiveData<List<Category>>()

    fun reqCategoryList() {
        val list = arrayListOf(
            Category("", "推荐"),
            Category("6809637769959178254", "后端"),
            Category("6809637767543259144", "前端"),
            Category("6809635626879549454", "Android"),
            Category("6809635626661445640", "iOS"),
            Category("6809637773935378440", "人工智能"),
            Category("6809637771511070734", "开发工具"),
            Category("6809637776263217160", "代码人生"),
            Category("6809637772874219534", "阅读")
        )
        categoryList.value = list
    }

}