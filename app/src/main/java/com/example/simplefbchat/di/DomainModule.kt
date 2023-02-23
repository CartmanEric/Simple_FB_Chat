package com.example.simplefbchat.di

import com.example.simplefbchat.data.RepositoryImpl
import com.example.simplefbchat.domain.FireBaseRepository
import dagger.Binds
import dagger.Module


@Module
interface DomainModule {
    @Binds
    fun bindFireBaseRepository(impl: RepositoryImpl): FireBaseRepository
}