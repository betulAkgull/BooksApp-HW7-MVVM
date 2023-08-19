package com.example.booksapp.ui.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.data.model.GetBookResponse
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.data.retrofit.retrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), BooksAdapter.BookListener {
    private lateinit var binding: FragmentHomeBinding
    private val booksAdapter by lazy { BooksAdapter(this) }

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

        getAllBooks()
        binding.rvBooks.adapter = booksAdapter
    }

    private fun getAllBooks() {
        retrofitClient.retrofit.getAllBooks().enqueue(object: Callback<GetBookResponse> {
            override fun onResponse(
                call: Call<GetBookResponse>,
                response: Response<GetBookResponse>
            ) {
                val getAllBooks = response.body()?.books
                if(getAllBooks.isNullOrEmpty().not())
                    booksAdapter.submitList(getAllBooks)
            }

            override fun onFailure(call: Call<GetBookResponse>, t: Throwable) {
                Log.e("getAllBooks", t.message.orEmpty())
            }

        })
    }

    override fun onBookClick(id: Int) {
        val direction = HomeFragmentDirections.actionHomeToDetail(id)
        findNavController().navigate(direction)
    }


}