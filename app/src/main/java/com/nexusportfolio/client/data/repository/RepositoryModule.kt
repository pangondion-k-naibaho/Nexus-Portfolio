package com.nexusportfolio.client.data.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//    @Singleton
//    @Provides
//    fun providePortfolioRepository(): PortfolioRepository = PortfolioRepositoryImpl()
//}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindPortfolioRepository(): PortfolioRepository = PortfolioRepositoryImpl()
}