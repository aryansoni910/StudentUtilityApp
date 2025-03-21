package com.example.project_1.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PasswordManager::class], version = 3, exportSchema = true)
abstract class PasswordManagerDataBase : RoomDatabase(){
    abstract fun dao() : Dao
}