package com.sokheang.mediaparknews.ui.article_search_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sokheang.mediaparknews.databinding.ActivityArticleSearchInBinding

class ArticleSearchInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleSearchInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleSearchInBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}