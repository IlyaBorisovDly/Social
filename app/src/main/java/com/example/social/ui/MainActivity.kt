package com.example.social.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social.databinding.ActivityMainBinding
import com.example.social.model.User

const val TAG = "myTag"

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels()
        viewModel.getUsers().observe(this, { users ->
            setupRecycler(users)
        })
    }

    private fun setupRecycler(users: List<User>) {
        val recyclerView = binding.recyclerViewUsers

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainScreenAdapter(users)
    }
}