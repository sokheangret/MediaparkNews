package com.sokheang.mediaparknews.ui.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sokheang.mediaparknews.R
import com.sokheang.mediaparknews.databinding.ItemArticleBinding
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.ui.article_web.ArticleWebActivity
import com.sokheang.mediaparknews.utils.Constants
import com.squareup.picasso.Picasso

/**
 * Create by Sokheang RET on 19-Aug-22.
 **/
class ArticleListAdapter (private val articleListData: List<ArticleListResponse.Article>) :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    inner class ViewHolder(val itemArticleBinding: ItemArticleBinding) : RecyclerView.ViewHolder(itemArticleBinding.root) {

        init {
            //item click go to web view activity
            itemArticleBinding.root.setOnClickListener {
                val intent = Intent(itemArticleBinding.root.context,ArticleWebActivity::class.java).apply {
                    putExtra(Constants.IntentConstants.ARTICLE_URL,articleListData[adapterPosition].url)
                }
                itemArticleBinding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        @BindingAdapter("android:loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, url: String) {
            Picasso
                .get()
                .load(url)
                .resize(124, 108)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemArticleBinding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemArticleBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemArticleBinding.article = articleListData[position]
    }

    override fun getItemCount(): Int = articleListData.size

}