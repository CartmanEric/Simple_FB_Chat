package com.example.simplefbchat.presentation

import android.app.Application
import com.example.simplefbchat.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}