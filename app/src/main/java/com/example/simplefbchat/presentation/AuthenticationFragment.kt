package com.example.simplefbchat.presentation


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplefbchat.R
import com.example.simplefbchat.databinding.FragmentAuthenticationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import javax.inject.Inject


class AuthenticationFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthenticationViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentAuthenticationBinding? = null
    private val binding: FragmentAuthenticationBinding
        get() = _binding ?: throw RuntimeException("FragmentAuthenticationBinding == null")

    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.injectAuthFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthenticationBinding.inflate(layoutInflater, container, false)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

