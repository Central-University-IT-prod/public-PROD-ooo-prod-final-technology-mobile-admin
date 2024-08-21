package com.prodtechnology.teammatchingadmin.ui.registration

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.User
import com.prodtechnology.teammatchingadmin.databinding.FragmentRegistrationBinding
import com.prodtechnology.teammatchingadmin.ui.home.HomeActivity
import com.prodtechnology.teammatchingadmin.utils.status.AuthStatus

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegistrationViewModel
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            RegistrationViewModelFactory(requireContext())
        )[RegistrationViewModel::class.java]


        viewModel.status.observe(viewLifecycleOwner){
            binding.llRegistrationLoading.visibility = View.GONE
            when(it){
                is AuthStatus.Succeed -> { onAuthSucceed(it.token) }
                is AuthStatus.Failed -> { onAuthFailed(it.error) }
            }
        }

        binding.btnSignUp.setOnClickListener {
            binding.inputLayoutRegistrationEmail.isErrorEnabled = false
            binding.inputLayoutRegistrationPassword.isErrorEnabled = false
            binding.inputLayoutRegistrationPasswordRepeat.isErrorEnabled = false

            if(!Patterns.EMAIL_ADDRESS.matcher(binding.etRegistrationEmail.text).matches()){
                binding.inputLayoutRegistrationEmail.error = getString(R.string.error_bad_email)
            }
            if(binding.etRegistrationPassword.text.length < 6){
                binding.inputLayoutRegistrationPassword.error = getString(R.string.error_short_password)
            }
            if(binding.etRegistrationPassword.text.toString() != binding.etRegistrationPasswordRepeat.text.toString()){
                binding.inputLayoutRegistrationPasswordRepeat.error = getString(R.string.error_password_not_match)
            }
            if(!binding.inputLayoutRegistrationEmail.isErrorEnabled
                && !binding.inputLayoutRegistrationPassword.isErrorEnabled
                && !binding.inputLayoutRegistrationPasswordRepeat.isErrorEnabled){
                binding.llRegistrationLoading.setOnClickListener {}
                binding.llRegistrationLoading.visibility = View.VISIBLE
                viewModel.signUpUser(
                    User(
                        binding.etRegistrationEmail.text.toString(),
                        binding.etRegistrationPassword.text.toString()
                    )
                )
            }
        }

        binding.tvToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        return binding.root
    }


    private fun onAuthSucceed(token: String) {
        AppPrefs.setToken(token)
        AppPrefs.setEmail(binding.etRegistrationEmail.text.toString())
        startActivity(Intent(requireContext(), HomeActivity::class.java))
    }

    private fun onAuthFailed(error: String){
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}