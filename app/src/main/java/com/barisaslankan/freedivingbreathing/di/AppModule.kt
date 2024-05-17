package com.barisaslankan.freedivingbreathing.di

import com.barisaslankan.freedivingbreathing.data.repository.RepositoryImpl
import com.barisaslankan.freedivingbreathing.domain.model.BreatheRecord
import com.barisaslankan.freedivingbreathing.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRealmDB() : Realm{
        return Realm.open(
            configuration = RealmConfiguration.create(
                setOf(BreatheRecord::class)
            )
        )
    }

    @Singleton
    @Provides
    fun provideRepository(realm : Realm) : Repository{
        return RepositoryImpl(realm = realm)
    }
}