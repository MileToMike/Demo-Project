package com.zonvoir.demoproject.universal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonvoir.demoproject.repository.UserRepository
import com.zonvoir.demoproject.viewModel.UserViewModel

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
