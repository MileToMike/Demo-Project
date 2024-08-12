package com.zonvoir.demoproject.repository

import com.zonvoir.demoproject.`interface`.ApiService
import com.zonvoir.demoproject.model.Comment
import com.zonvoir.demoproject.model.Post
import com.zonvoir.demoproject.model.User

class UserRepository(private val apiService: ApiService) {

    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    suspend fun getUserPosts(userId: Int): List<Post> {
        return apiService.getUserPosts(userId)
    }

    suspend fun createPost(post: Post): Post {
        return apiService.createPost(post)
    }

    suspend fun addComment(comment: Comment): Comment {
        return apiService.addComment(comment)
    }

    suspend fun getUserDetails(userId: Int): User {
        return apiService.getUserById(userId)
    }
}
