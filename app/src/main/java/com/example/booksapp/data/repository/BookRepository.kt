package com.example.booksapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BookDetail
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.data.model.GetBookResponse
import com.example.booksapp.data.source.BookService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository(private val bookService: BookService) {

    val booksLiveData = MutableLiveData<List<Book>?>()
    val bookDetailLiveData = MutableLiveData<BookDetail?>()
    val errorMessageLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getAllBooks() {
        loadingLiveData.value = true

        bookService.getAllBooks().enqueue(object : Callback<GetBookResponse> {
            override fun onResponse(
                call: Call<GetBookResponse>,
                response: Response<GetBookResponse>
            ) {
                val getAllBooks = response.body()?.books

                if (getAllBooks.isNullOrEmpty().not()) {
                    booksLiveData.value = getAllBooks
                } else {
                    booksLiveData.value = null
                }

                loadingLiveData.value = false

            }

            override fun onFailure(call: Call<GetBookResponse>, t: Throwable) {
                loadingLiveData.value = false
                errorMessageLiveData.value = t.message.orEmpty()
                Log.e("getAllBooks", t.message.orEmpty())
            }

        })
    }

    fun getBookDetail(id: Int) {
        loadingLiveData.value = true

        bookService.getBookDetail(id).enqueue(object : Callback<GetBookDetailResponse> {
            override fun onResponse(
                call: Call<GetBookDetailResponse>,
                response: Response<GetBookDetailResponse>
            ) {
                bookDetailLiveData.value = response.body()?.book
                loadingLiveData.value = false

            }

            override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                loadingLiveData.value = false
                errorMessageLiveData.value = t.message.orEmpty()
                Log.e("GetDetailBook", t.message.orEmpty())
            }

        })
    }
}