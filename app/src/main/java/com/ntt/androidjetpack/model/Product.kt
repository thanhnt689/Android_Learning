package com.ntt.androidjetpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "p_id")
    val id: Int = 0,
    @ColumnInfo(name = "p_name")
    val name: String,
    @ColumnInfo(name = "p_description")
    val description: String,
    @ColumnInfo(name = "p_price")
    val price: String,
    @ColumnInfo(name = "p_amount")
    val amount: Int
)