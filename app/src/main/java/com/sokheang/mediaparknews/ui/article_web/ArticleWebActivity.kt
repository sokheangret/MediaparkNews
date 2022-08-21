package com.sokheang.mediaparknews.ui.article_web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sokheang.mediaparknews.databinding.ActivityArticleWebBinding
import com.sokheang.mediaparknews.utils.Constants

class ArticleWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleUrl = intent.getStringExtra(Constants.IntentConstants.ARTICLE_URL)

        binding.webView.loadUrl(articleUrl!!)

    }
}