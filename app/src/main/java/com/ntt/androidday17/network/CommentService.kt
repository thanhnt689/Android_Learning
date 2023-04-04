package com.ntt.androidday17.network

import com.ntt.androidday17.model.Comment
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.*

interface CommentService {

    @GET("comments/")
    fun getAllComment(): Call<List<Comment>>

    @GET("comments/")
    suspend fun getAllCommentWithCoroutines(): List<Comment>

    @GET("comments/")
    fun getAllCommentWithRx(): Observable<List<Comment>>

    @GET("comments/{id}")
    fun getCommentById(@Path("id") postId: String): Call<Comment>


    @POST("posts")
    @Headers("Content-Type: application/json", "Content-Length: 15402")
    fun addComment(@Header("Content_type") postId: String, @Body comment: Comment)

    @GET("comments/")
    suspend fun getCommentWithQuery(
        @Query("user_id") userId: Int,
        @Query("field") searchField: String,
        @Query("sort") sortType: String
    ): List<Comment>

    @PUT("comment/{id}")
    suspend fun updateComment(@Path("id") id: Int, @Body comment: Comment)

    @PATCH("comment/{id}")
    suspend fun updateComment2(@Path("id") id: Int, @Body comment: Comment)

    @DELETE("comment/{id}")
    suspend fun deleteComment(@Path("id") id: Int)
}