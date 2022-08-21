package com.sokheang.mediaparknews.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sokheang.mediaparknews.databinding.FragmentSearchBinding
import com.sokheang.mediaparknews.databinding.ItemSearchHistoryBinding
import com.sokheang.mediaparknews.room.data.SearchHistory

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/
class SearchHistoryAdapter (private val searchHistoryListData: List<SearchHistory>) :
RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
    inner class ViewHolder(val itemSearchBinding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(itemSearchBinding.root) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemSearchHistory = ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemSearchHistory)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemSearchBinding.searchHistory = searchHistoryListData[position]
    }

    override fun getItemCount(): Int = searchHistoryListData.size
}