package com.putragandad.moviedbch5.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.putragandad.moviedbch5.databinding.FragmentProfileEditBinding
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import org.koin.android.ext.android.inject

class ProfileEditFragment : Fragment() {
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    private val userViewModel : UserViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvFullname = binding.etEditprofileFullname
        val tvUsername = binding.etEditprofileUsername
        val tvEmail = binding.etEditprofileEmail

        userViewModel.userInfo.observe(viewLifecycleOwner) { (email, fullname, username) ->
            tvFullname.editText?.setText(fullname)
            if(username.isNotEmpty()) tvUsername.editText?.setText(username)
            tvEmail.editText?.setText(email)
        }

        binding.btnEditProfile.setOnClickListener {
            saveProfile(tvFullname, tvUsername, tvEmail)
        }

        binding.btnBackRegister.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun saveProfile(fullname: TextInputLayout, username: TextInputLayout, email: TextInputLayout) {
        val getFullname = fullname.editText?.text.toString()
        val getUsername = username.editText?.text.toString()
        val getEmail = email.editText?.text.toString()
        if(getFullname.isNotEmpty() && getEmail.isNotEmpty()) {
            userViewModel.saveAccountDetail(getUsername, getFullname, getEmail)
            findNavController().popBackStack()
            Snackbar.make(requireView(), "Profile successfully saved.", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(requireView(), "Email / Fullname field can't be empty!", Snackbar.LENGTH_LONG).show()
        }
    }
}