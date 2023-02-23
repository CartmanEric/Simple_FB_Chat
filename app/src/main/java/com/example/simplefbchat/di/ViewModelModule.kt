package com.example.simplefbchat.di

import androidx.lifecycle.ViewModel
import com.example.simplefbchat.presentation.AuthenticationViewModel
import com.example.simplefbchat.presentation.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    @Binds
    fun bindStartViewModel(impl: AuthenticationViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    @Binds
    fun bindAddViewModel(impl: ChatViewModel): ViewModel

}