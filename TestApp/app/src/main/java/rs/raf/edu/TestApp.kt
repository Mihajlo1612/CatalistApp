package rs.raf.edu

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("Test", "App:onCreate()")
    }
}