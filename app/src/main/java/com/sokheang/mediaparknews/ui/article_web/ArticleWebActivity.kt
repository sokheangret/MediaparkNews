package com.sokheang.mediaparknews.ui.article_web

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.sokheang.mediaparknews.databinding.ActivityArticleWebBinding
import com.sokheang.mediaparknews.utils.Constants


class ArticleWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleUrl = intent.getStringExtra(Constants.IntentConstants.ARTICLE_URL)

        binding.toolbar.textClear.visibility = View.INVISIBLE
        binding.toolbar.buttonBack.setOnClickListener {
            onBackPressed()
        }

        binding.webView.loadUrl(articleUrl!!)

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress == 100) { //...page is fully loaded.
                    binding.includeLayoutLoading.linearLoading.visibility = View.GONE
                }
            }
        }

    }
}