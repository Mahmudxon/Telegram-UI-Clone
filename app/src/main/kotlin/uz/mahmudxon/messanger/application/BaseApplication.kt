package uz.mahmudxon.messanger.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        System.loadLibrary("messanger")
    }
}