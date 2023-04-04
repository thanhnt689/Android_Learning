package com.ntt.androidjetpack.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ntt.androidjetpack.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM tb_product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM tb_product")
    fun getAllProductsLiveData(): LiveData<List<Product>>
}