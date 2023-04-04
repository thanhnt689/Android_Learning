package com.ntt.androidday18.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int = 0,
    @ColumnInfo(name = "_name")
    val name: String = "",
    @ColumnInfo(name = "_account")
    val account: String = "",
    @ColumnInfo(name = "_password")
    val password: String = "",
    @Ignore
    val role: Int = 0
)