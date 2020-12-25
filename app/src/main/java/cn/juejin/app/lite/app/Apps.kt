package cn.juejin.app.lite.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import cn.juejin.app.lite.manager.HttpManager
import cn.juejin.app.lite.manager.ServiceLoaderManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

inline fun <reified T> api(): T = HttpManager.create(T::class.java)

inline fun <reified T> service(): T? = ServiceLoaderManager.getService(T::class.java)

fun <T> FragmentActivity.launch(
    block: suspend () -> T,
    success: suspend (T) -> Unit,
    fail: suspend (Exception) -> Unit = {}
) = launch(lifecycleScope, block, success, fail)

fun <T> Fragment.launch(
    block: suspend () -> T,
    success: suspend (T) -> Unit,
    fail: suspend (Exception) -> Unit = {}
) = launch(lifecycleScope, block, success, fail)

fun <T> ViewModel.launch(
    block: suspend () -> T,
    success: suspend (T) -> Unit,
    fail: suspend (Exception) -> Unit = {}
) = launch(viewModelScope, block, success, fail)

fun <T> launch(
    block: suspend () -> T,
    success: suspend (T) -> Unit,
    fail: suspend (Exception) -> Unit = {}
) = launch(GlobalScope, block, success, fail)

private fun <T> launch(
    scope: CoroutineScope, block: suspend () -> T,
    success: suspend (T) -> Unit,
    fail: suspend (Exception) -> Unit
) = scope.launch {
    try {
        val result = block()
        success(result)
    } catch (t: Exception) {
        fail(t)
    }
}