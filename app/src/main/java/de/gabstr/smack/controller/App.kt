package de.gabstr.smack.controller

import android.app.Application
import de.gabstr.smack.utilities.SharedPrefs

class App : Application(){

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}