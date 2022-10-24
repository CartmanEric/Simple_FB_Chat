package com.example.simplefbchat.screen


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.simplefbchat.R
import com.example.simplefbchat.databinding.FragmentAuthenticationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


class AuthenticationFragment : Fragment() {
    lateinit var binding: FragmentAuthenticationBinding
    private val viewModel: AuthenticationViewModel by activityViewModels()
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater, container, false)
        val controller = findNavController()
        if (viewModel.checkAuthCondition()) {
            controller.navigate(R.id.chatFragment)
        } else {
            launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val account = task.getResult(ApiException::class.java)
                viewModel.toGetAuth(account.idToken!!)
            }
            val signInClient = viewModel.getAuthForIntend(requireActivity())
            launcher.launch(signInClient)
        }
        binding.enterBt.setOnClickListener {
            if (viewModel.checkAuthCondition()) {
                controller.navigate(R.id.chatFragment)
            } else {
                Toast.makeText(requireContext(), "Войдите в аккаунт", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }


}

