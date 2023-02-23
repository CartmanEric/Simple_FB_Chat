package com.example.simplefbchat.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplefbchat.databinding.FragmentAuthenticationBinding
import com.example.simplefbchat.databinding.FragmentChatBinding
import com.example.simplefbchat.presentation.adapter.UserAdapter
import javax.inject.Inject


class ChatFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ChatViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding ?: throw RuntimeException("FragmentChatBinding == null")

    lateinit var adapter: UserAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.injectChatFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        initRV()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendTextBt.setOnClickListener {
            viewModel.setText(binding.ed.text.toString())
            binding.ed.setText("")
        }

        viewModel.allUserMessage.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initRV() {
        adapter = UserAdapter()
        val manager = LinearLayoutManager(requireActivity())
        manager.stackFromEnd = true
        binding.rcView.layoutManager = manager
        binding.rcView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}