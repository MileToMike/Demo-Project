package com.zonvoir.demoproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonvoir.demoproject.model.User
import com.zonvoir.demoproject.repository.UserRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun fetchUserDetails(userId: Int) {
        viewModelScope.launch {
            try {
                val userDetails = repository.getUserDetails(userId)
                _user.postValue(userDetails)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
