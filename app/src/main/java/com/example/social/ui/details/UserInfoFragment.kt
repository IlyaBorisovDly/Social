package com.example.social.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.social.databinding.FragmentUserInfoBinding
import com.example.social.model.entities.User

class UserInfoFragment: Fragment() {

    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        user = arguments?.getParcelable(USER_ARGUMENT)

        bindUserInfo()
        setOnClickListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindUserInfo() {
        user?.let {
            with(binding) {
                textViewName.text = it.name
                textViewAge.text = it.getFormattedAge()
                textViewUserCompany.text = it.company
                textViewUserMail.text = it.email
                textViewUserPhone.text = it.phone
                textViewUserAddress.text = it.address
                textViewUserCoordinates.text = it.getCoordinates()
                textViewUserRegistration.text = it.getFormattedRegistrationDate()
                imageViewEyeColor.setImageResource(it.getEyeColorSource())
                imageViewFavouriteFruit.setImageResource(it.getFavouriteFruitSource())
                textViewUserAbout.text = it.about
            }
        }
    }

    private fun setOnClickListeners() {
        setOnMailClickListener()
        setOnPhoneClickListener()
        setOnCoordinatesClickListener()
    }

    private fun setOnCoordinatesClickListener() {
        binding.textViewUserCoordinates.setOnClickListener {
            val coordinatesIntent = Intent(Intent.ACTION_VIEW)
            val data = Uri.parse("geo:${user?.getCoordinates()}")

            coordinatesIntent.data = data

            startActivity(coordinatesIntent)
        }
    }

    private fun setOnPhoneClickListener() {
        binding.textViewUserPhone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            val data = Uri.parse("tel:${user?.phone}")

            phoneIntent.data = data

            startActivity(phoneIntent)
        }
    }

    private fun setOnMailClickListener() {
        binding.textViewUserMail.setOnClickListener {
            val mailIntent = Intent(Intent.ACTION_SENDTO)
            val data = Uri.parse("mailto:${user?.email}")

            mailIntent.data = data

            startActivity(mailIntent)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(user: User) =
            UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_ARGUMENT, user)
                }
            }
    }
}