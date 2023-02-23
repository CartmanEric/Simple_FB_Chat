package com.example.simplefbchat.presentation.screen


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplefbchat.R
import com.example.simplefbchat.databinding.FragmentAuthenticationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class AuthenticationFragment : Fragment() {
    private lateinit var viewModel: AuthenticationViewModel
    lateinit var binding: FragmentAuthenticationBinding
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        checkAuthCondition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enterBt.setOnClickListener {
            navigate()
        }
    }

    private fun navigate() {
        findNavController().navigate(R.id.chatFragment)
    }

    private fun launcherStart() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val account = task.getResult(ApiException::class.java)
            account.idToken?.let { viewModel.putToken(it) }
        }
        launcher.launch(viewModel.getAuthIntend)

    }

    private fun checkAuthCondition() {
        if (viewModel.checkAuthCondition) {
            navigate()
        } else {
            launcherStart()
        }
    }

}

