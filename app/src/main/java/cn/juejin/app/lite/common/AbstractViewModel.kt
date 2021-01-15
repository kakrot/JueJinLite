package cn.juejin.app.lite.common

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel

abstract class AbstractViewModel(application: Application) : AndroidViewModel(application) {

    open fun onRestoreBundle(args: Bundle?) {

    }

    open fun onSaveBundle(args: Bundle) {

    }

}