package com.example.booksapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.BookDetail
import com.example.booksapp.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val bookRepository: BookRepository): ViewModel() {

    private var _bookDetailLiveData = MutableLiveData<BookDetail?>()
    val bookDetailLiveData: LiveData<BookDetail?>
        get() = _bookDetailLiveData


    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        _bookDetailLiveData = bookRepository.bookDetailLiveData
        _errorMessageLiveData = bookRepository.errorMessageLiveData
        _loadingLiveData = bookRepository.loadingLiveData
    }

    fun getBookDetail(id:Int){
        bookRepository.getBookDetail(id)
    }
}