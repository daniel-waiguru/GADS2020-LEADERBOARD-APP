package tech.danielwaiguru.gads2020

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    /**
     * initialize Timber and override createStackElementTag method to
     * create a more debugging friendly stack trace message
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG){
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + "-->" + element.methodName + "-->" +
                            element.lineNumber
                }
            })
        }
    }

}