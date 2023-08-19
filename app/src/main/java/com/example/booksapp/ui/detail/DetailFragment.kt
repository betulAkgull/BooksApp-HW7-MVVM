package com.example.booksapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.booksapp.R
import com.example.booksapp.common.gone
import com.example.booksapp.common.loadImage
import com.example.booksapp.common.visible
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.data.model.GetBookResponse
import com.example.booksapp.databinding.FragmentDetailBinding
import com.example.booksapp.data.retrofit.retrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetailBook(args.bookId)
    }

    private fun getDetailBook(id:Int) {
        retrofitClient.retrofit.getBookDetail(id).enqueue(object : Callback<GetBookDetailResponse> {
            override fun onResponse(
                call: Call<GetBookDetailResponse>,
                response: Response<GetBookDetailResponse>
            ) {
                val detailBooks = response.body()?.book
                if (detailBooks != null) {
                    with(binding) {
                        ivBook.loadImage(detailBooks.imageUrl)
                        tvBookName.text = detailBooks.name
                        tvAuthorName.text = detailBooks.author
                        tvPublisherName.text = detailBooks.publisher
                        tvPrice.text = "${detailBooks.price} ₺"
                    }
                }
            }

            override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                Log.e("GetDetailBook", t.message.orEmpty())
            }

        })
    }
}