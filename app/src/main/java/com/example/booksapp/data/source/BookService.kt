package com.example.booksapp.data.source

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.booksapp.common.Constants.Endpoint.GET_ALL_BOOKS
import com.example.booksapp.common.Constants.Endpoint.GET_BOOKS_DETAIL
import com.example.booksapp.data.model.GetBookDetailResponse
import com.example.booksapp.data.model.GetBookResponse
import retrofit2.http.Query


interface BookService {
    @GET(GET_ALL_BOOKS)
    fun getAllBooks(): Call<GetBookResponse>

    @GET(GET_BOOKS_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>
}