package com.example.booksapp.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.common.visible
import com.example.booksapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BooksAdapter.BookListener {

    private lateinit var binding: FragmentHomeBinding

    private val booksAdapter by lazy { BooksAdapter(this) }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = booksAdapter

        viewModel.getAllBooks()
        observeData()
    }

    private fun observeData(){
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = it
        }

        viewModel.booksLiveData.observe(viewLifecycleOwner){ list ->
            if(list != null){
                 booksAdapter.submitList(list)
            }else{
                Toast.makeText(requireContext(), "empty list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBookClick(id: Int) {
        val direction = HomeFragmentDirections.actionHomeToDetail(id)
        findNavController().navigate(direction)
    }


}