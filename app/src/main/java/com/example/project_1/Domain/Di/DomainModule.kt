package com.example.project_1.Domain.Di

import android.app.Application
import com.example.project_1.Data.Database.PasswordManagerDataBase
import com.example.project_1.Data.Repo.RepoImpl
import com.example.project_1.Domain.Repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

class DomainModule {


    @Provides
    fun provideRepo(firebaseAuth: FirebaseAuth,firebaseFirestore: FirebaseFirestore,passwordManagerDataBase: PasswordManagerDataBase,application: Application): Repo {
        return RepoImpl(
            firebaseAuth,firebaseFirestore,passwordManagerDataBase, application
        )

    }
}