package com.example.project_1.Data.Di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_1.Data.Database.PasswordManagerDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideDataBase(application: Application): PasswordManagerDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            PasswordManagerDataBase::class.java,
            name = "password_manager"
        ).fallbackToDestructiveMigration().build()
    }
}