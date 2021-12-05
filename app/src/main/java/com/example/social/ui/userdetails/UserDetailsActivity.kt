package com.example.social.ui.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.social.databinding.ActivityUserDetailsBinding
import com.example.social.model.User
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUserDetailsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = intent.extras?.getParcelable<User>("user")!!
        val pagerAdapter = PagerAdapter(this, user)

        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabNames = listOf("Информация", "Друзья")
            tab.text = tabNames[position]
        }.attach()
    }
}