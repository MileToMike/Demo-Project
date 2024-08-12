package com.zonvoir.demoproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonvoir.demoproject.model.Comment
import com.zonvoir.demoproject.model.Post
import com.zonvoir.demoproject.model.User
import com.zonvoir.demoproject.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _selectedUser = MutableLiveData<User>()
    val selectedUser: LiveData<User> get() = _selectedUser

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    fun fetchUsers() {
        viewModelScope.launch {
            _users.value = repository.getUsers()
        }
    }

    fun selectUser(user: User) {
        _selectedUser.value = user
        fetchUserPosts(user.id)
    }

    fun fetchUserPosts(userId: Int) {
        viewModelScope.launch {
            _posts.value = repository.getUserPosts(userId)
        }
    }

    fun createPost(post: Post) {
        viewModelScope.launch {
            repository.createPost(post)
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch {
            repository.addComment(comment)
        }
    }
}
