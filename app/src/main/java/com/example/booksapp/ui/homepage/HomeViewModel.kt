package com.example.booksapp.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bookRepository: BookRepository) : ViewModel() {

    private var _booksLiveData = MutableLiveData<List<Book>?>()
    val booksLiveData: LiveData<List<Book>?>
        get() = _booksLiveData


    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        _booksLiveData = bookRepository.booksLiveData
        _errorMessageLiveData = bookRepository.errorMessageLiveData
        _loadingLiveData = bookRepository.loadingLiveData
    }

    fun getAllBooks(){
        bookRepository.getAllBooks()

    }
}