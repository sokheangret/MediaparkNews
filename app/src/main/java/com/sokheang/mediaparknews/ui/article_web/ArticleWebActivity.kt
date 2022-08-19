package com.sokheang.mediaparknews.ui.article_web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sokheang.mediaparknews.databinding.ActivityArticleWebBinding

class ArticleWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleUrl = intent.getStringExtra("ARTICLE_URL")

        binding.webView.loadUrl(articleUrl!!)

    }
}