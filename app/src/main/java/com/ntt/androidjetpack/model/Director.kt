package com.ntt.androidjetpack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Director(
    @PrimaryKey
    val name: String,
    val schoolName: String
)
