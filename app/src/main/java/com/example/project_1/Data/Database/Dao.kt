package com.example.project_1.Data.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Upsert
    suspend fun upsertPassword(passwordManager: PasswordManager)

    @Delete
    suspend fun deletePassword(passwordManager: PasswordManager)

    @Query("SELECT * FROM passwordmanager ")
    fun getPassword(): Flow<List<PasswordManager>>


}