package com.example.social.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social.databinding.FragmentUserInfoBinding
import com.example.social.model.User

class UserInfoFragment(private val user: User): Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        bindUserInfo()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindUserInfo() {
        val coordinates = "${user.latitude}, ${user.longitude}"

        with(binding) {
            textViewName.text = user.name
            textViewAge.text = user.age.toString()
            textViewUserCompany.text = user.company
            textViewUserMail.text = user.email
            textViewUserPhone.text = user.phone
            textViewUserAddress.text = user.address
            textViewUserCoordinates.text = coordinates
            textViewUserRegistration.text = user.registered
            textViewUserAbout.text = user.about
        }
    }
}