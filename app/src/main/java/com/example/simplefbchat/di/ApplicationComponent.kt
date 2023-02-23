package com.example.simplefbchat.di

import android.app.Application
import com.example.simplefbchat.presentation.AuthenticationFragment
import com.example.simplefbchat.presentation.ChatFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DomainModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun injectAuthFragment(authFragment: AuthenticationFragment)
    fun injectChatFragment(chatFragment: ChatFragment)


    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent

    }

}