package com.prodtechnology.teammatchingadmin.ui.account_info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.prodtechnology.teammatchingadmin.activity.AccountActivity
import com.prodtechnology.teammatchingadmin.R
import com.prodtechnology.teammatchingadmin.data.local.AppPrefs
import com.prodtechnology.teammatchingadmin.data.remote.models.ChangePasswordRequest
import com.prodtechnology.teammatchingadmin.databinding.FragmentAccountInfoBinding
import com.prodtechnology.teammatchingadmin.utils.status.AuthStatus

class AccountInfoFragment : Fragment() {

    private var _binding: FragmentAccountInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AccountInfoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountInfoBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            AccountInfoViewModelFactory(requireContext())
        )[AccountInfoViewModel::class.java]

        viewModel.passwordStatus.observe(viewLifecycleOwner){
            when(it){
                is AuthStatus.Succeed -> {onPasswordChangeSucceed(it.token)}
                is AuthStatus.Failed -> {onPasswordChangeFailed(it.error)}
            }
        }

        binding.tvAccountEmail.text = AppPrefs.getEmail()

        binding.btnChangePassword.setOnClickListener {
            viewModel.updatePassword(
                AppPrefs.getToken()!!,
                ChangePasswordRequest(
                    binding.etAccountInfoOldPassword.text.toString(),
                    binding.etAccountInfoNewPassword.text.toString()
                )
            )
        }

        binding.btnLogOut.setOnClickListener {
            AppPrefs.setToken(null)
            AppPrefs.setEmail("")
            Snackbar.make(
                requireView(),
                getString(R.string.message_are_you_sure),
                Snackbar.ANIMATION_MODE_SLIDE
            ).setAction(getString(R.string.text_yes)){
                val i =Intent(requireContext(), AccountActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
                requireActivity().finish()
            }.show()
        }
        return binding.root
    }

    private fun onPasswordChangeSucceed(newToken: String){
        AppPrefs.setToken(newToken)
        Toast.makeText(
            requireContext(),
            getString(R.string.text_password_changed_successfully),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun onPasswordChangeFailed(error: String){
        Toast.makeText(
            requireContext(),
            error,
            Toast.LENGTH_LONG
        ).show()
    }
}