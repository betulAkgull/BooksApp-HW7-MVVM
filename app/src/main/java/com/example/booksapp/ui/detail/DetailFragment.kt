package com.example.booksapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.booksapp.common.loadImage
import com.example.booksapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBookDetail(args.bookId)
        observeData()
    }

    private fun observeData() = with(binding) {

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            binding.progressBar2.isVisible = it
        }


        viewModel.bookDetailLiveData.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                ivBook.loadImage(book.imageUrl)
                tvBookName.text = book.name
                tvAuthorName.text = book.author
                tvPublisherName.text = book.publisher
                tvPrice.text = "${book.price} ₺"

            } else {
                Toast.makeText(requireContext(), "Book not found !", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}