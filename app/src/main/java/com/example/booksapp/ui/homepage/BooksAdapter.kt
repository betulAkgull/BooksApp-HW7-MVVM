package com.example.booksapp.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.common.gone
import com.example.booksapp.common.loadImage
import com.example.booksapp.common.visible
import com.example.booksapp.data.model.Book
import com.example.booksapp.databinding.ItemBookBinding

class BooksAdapter (
    private val bookListener: BookListener
) : ListAdapter<Book, BooksAdapter.BookViewHolder>(BookDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            bookListener
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) = holder.bind(getItem(position))

    class BookViewHolder (
        private val binding: ItemBookBinding,
        private val bookListener: BookListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind (book:Book) = with(binding) {
            tvTitle.text = book.name
            tvPrice.text = "${book.price} ₺"
            ivBook.loadImage(book.imageUrl)
            root.setOnClickListener {
                bookListener.onBookClick(book.id ?: 1)
            }
            if(book.isBestSeller == true)
                tvBestSellerIcon.visible()
            else tvBestSellerIcon.gone()
        }
    }

    class BookDiffCallBack: DiffUtil.ItemCallback<Book>() {
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface BookListener {
        fun onBookClick(id: Int)
    }
}

