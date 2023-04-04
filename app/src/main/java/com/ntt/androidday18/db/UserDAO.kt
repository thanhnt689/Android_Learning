package com.ntt.androidday18.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ntt.androidday18.db.entity.User

@Dao
interface UserDAO {
    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>
}