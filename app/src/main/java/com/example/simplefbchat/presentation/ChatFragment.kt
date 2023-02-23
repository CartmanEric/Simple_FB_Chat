package com.example.simplefbchat.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplefbchat.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by activityViewModels()
    lateinit var adapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
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

}