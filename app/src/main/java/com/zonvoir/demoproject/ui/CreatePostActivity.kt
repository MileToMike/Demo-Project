package com.zonvoir.demoproject.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zonvoir.demoproject.repository.UserRepository
import com.zonvoir.demoproject.universal.RetrofitClient

class CreatePostActivity : AppCompatActivity() {

//    private lateinit var binding: A
//    private val postViewModel: PostViewModel by viewModels {
//        ViewModelFactory(UserRepository(RetrofitClient.apiService))
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityCreatePostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnSubmit.setOnClickListener {
//            val title = binding.etTitle.text.toString()
//            val body = binding.etBody.text.toString()
//
//            postViewModel.createPost(title, body)
//        }
//
//        postViewModel.postCreationStatus.observe(this) { isSuccess ->
//            if (isSuccess) {
//                Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Failed to create post", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
