package com.ntt.androidjetpack.di

import android.app.Application
import androidx.room.Room
import com.ntt.androidjetpack.db.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            ProductDatabase::class.java,
            "product.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: ProductDatabase): ProductDao {
        return db.getProductDao()
    }
}