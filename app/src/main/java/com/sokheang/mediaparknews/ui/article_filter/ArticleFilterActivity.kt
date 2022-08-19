package com.sokheang.mediaparknews.ui.article_filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sokheang.mediaparknews.databinding.ActivityArticleFilterBinding
import com.sokheang.mediaparknews.ui.article_search_in.ArticleSearchInActivity

class ArticleFilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutSearchIn.setOnClickListener {
            val intent = Intent(this@ArticleFilterActivity, ArticleSearchInActivity::class.java)
            startActivity(intent)
        }

    }
}