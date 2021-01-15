package cn.juejin.app.lite.app

import android.app.Application
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk


class App:Application() {

    override fun onCreate() {
        super.onCreate()
        initWebView()
    }

    private fun initWebView(){
        val map = mapOf<String,Any>(
            TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER to true,
            TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE to true
        )
        QbSdk.initTbsSettings(map)
    }
}