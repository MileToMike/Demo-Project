package com.zonvoir.demoproject.`interface`

import com.zonvoir.demoproject.model.Comment
import com.zonvoir.demoproject.model.Post
import com.zonvoir.demoproject.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getUserPosts(@Query("userId") userId: Int): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @POST("comments")
    suspend fun addComment(@Body comment: Comment): Comment

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): User
}
