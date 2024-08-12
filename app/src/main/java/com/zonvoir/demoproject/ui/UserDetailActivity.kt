package com.zonvoir.demoproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonvoir.demoproject.databinding.ActivityUserDetailBinding
import com.zonvoir.demoproject.repository.UserRepository
import com.zonvoir.demoproject.universal.RetrofitClient
import com.zonvoir.demoproject.viewModel.UserDetailsViewModel
import com.zonvoir.demoproject.viewModel.UserDetailsViewModelFactory

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra(EXTRA_USER_ID, -1)
        if (userId == -1) {
            // Handle error: no user ID provided
            finish()
            return
        }

        val apiService = RetrofitClient.apiService
        val repository = UserRepository(apiService)
        userDetailsViewModel = ViewModelProvider(this, UserDetailsViewModelFactory(repository)).get(UserDetailsViewModel::class.java)

        userDetailsViewModel.user.observe(this, Observer { user ->
            user?.let {
                binding.user = it
            }
        })

        userDetailsViewModel.fetchUserDetails(userId)
    }

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }
}
