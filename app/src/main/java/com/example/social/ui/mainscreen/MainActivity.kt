package com.example.social.ui.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social.databinding.ActivityMainBinding
import com.example.social.model.User
import com.example.social.ui.CallbackListener
import com.example.social.ui.MainAdapter
import com.example.social.ui.userdetails.UserDetailsActivity

const val TAG = "myTag"

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels()

        viewModel.users.observe(this, { users ->
            setupRecycler(users)
        })
    }

    private fun setupRecycler(users: List<User>) {
        val recyclerView = binding.recyclerViewUsers
        val callbackListener = getCallbackListener()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(users, callbackListener)
    }

    private fun getCallbackListener(): CallbackListener {
        return object: CallbackListener {
            override fun onItemClicked(user: User) {
                val intent = Intent(application, UserDetailsActivity::class.java)

                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
    }
}

