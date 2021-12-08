package com.example.social.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social.databinding.FragmentUserInfoBinding
import com.example.social.model.entities.EyeColor
import com.example.social.model.entities.User

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
        with(binding) {
            textViewName.text = user.name
            textViewAge.text = user.age.toString()
            textViewUserCompany.text = user.company
            textViewUserMail.text = user.email
            textViewUserPhone.text = user.phone
            textViewUserAddress.text = user.address
            textViewUserCoordinates.text = user.getCoordinates()
            textViewUserRegistration.text = user.getFormattedRegistrationDate()
            imageViewEyeColor.setImageResource(user.getEyeColorSource())
            imageViewFavouriteFruit.setImageResource(user.getFavouriteFruitSource())
            textViewUserAbout.text = user.about
        }
    }


}