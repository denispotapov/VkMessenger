package com.example.vkmessenger.di

import android.app.Application
import android.content.Context
import com.example.vkmessenger.VkDefaultRepository
import com.example.vkmessenger.VkRepository
import com.example.vkmessenger.local.VkLocalDataSource
import com.example.vkmessenger.local.VkRoomDataSource
import com.example.vkmessenger.network.VkNetworkDataSource
import com.example.vkmessenger.network.VkRetrofitDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {

    @Singleton
    @Binds
    @AppContext
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindVkNetworkDataSource(vkRetrofitDataSource: VkRetrofitDataSource): VkNetworkDataSource

    @Binds
    abstract fun bindVkLocalDataSource(friendsRoomDataSource: VkRoomDataSource): VkLocalDataSource

    @Binds
    abstract fun bindVkRepository(friendsDefaultRepository: VkDefaultRepository): VkRepository
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ServiceScoped

