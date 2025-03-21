package com.example.project_1.Data.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "passwordmanager")
data class PasswordManager(
    @PrimaryKey(autoGenerate = true) var id: Int =0,
    var title: String,
    var user_name:String,
    var password: String,
    )