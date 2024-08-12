package com.zonvoir.demoproject.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zonvoir.demoproject.R
import com.zonvoir.demoproject.repository.UserRepository
import com.zonvoir.demoproject.universal.RetrofitClient
import com.zonvoir.demoproject.viewModel.UserViewModel
import com.zonvoir.demoproject.ui.adapter.UserAdapter
import com.zonvoir.demoproject.universal.UserViewModelFactory

class UserListActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    lateinit var button:FloatingActionButton
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        dialog = Utils.getProgressDialog(this@UserListActivity)
        dialog?.show()

        val apiService = RetrofitClient.apiService
        val repository = UserRepository(apiService)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val searchView: SearchView = findViewById(R.id.searchEditText)
        button = findViewById(R.id.button)

        adapter = UserAdapter { user -> userViewModel.selectUser(user) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        userViewModel.users.observe(this, Observer { users ->
            dialog?.dismiss()
            Log.d("UserListActivity", "Users list updated: $users")
            adapter.submitList(users)
        })

        userViewModel.fetchUsers()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterUsers(it) }
                return true
            }
        })
        onClick()
    }

    private fun onClick(){
        button.setOnClickListener {
            val intent = Intent(this,CreatePostActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun filterUsers(query: String) {
        userViewModel.users.value?.let { users ->
            val filteredUsers = users.filter { it.name.contains(query, ignoreCase = true) }
            adapter.submitList(filteredUsers)
        }
    }
}
