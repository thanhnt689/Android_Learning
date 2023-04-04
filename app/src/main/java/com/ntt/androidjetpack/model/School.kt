package com.ntt.androidjetpack.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class School(
    @PrimaryKey
    val schoolName: String,
    val address: String
)