package com.sokheang.mediaparknews.ui.article_search_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.ActivityArticleSearchInBinding
import com.sokheang.mediaparknews.ui.article_filter.ArticleFilterViewModel
import com.sokheang.mediaparknews.utils.Constants
import javax.inject.Inject

class ArticleSearchInActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: ArticleSearchInViewModel

    private lateinit var binding: ActivityArticleSearchInBinding
    private var searchIn = ""
    private val searchList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleSearchInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MediaparkNewsApp).getApplicationComponent().inject(this)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        searchIn = intent.getStringExtra(Constants.IntentConstants.SEARCH_IN).toString()

        if(searchIn.contains("title")) {
            viewModel.titleCheck.value = true
        }

        if(searchIn.contains("description")) {
            viewModel.descriptionCheck.value = true
        }

        if(searchIn.contains("content")) {
            viewModel.contentCheck.value = true
        }

        viewModel.titleCheck.observe(this) {
            if (it) searchList.add("title") else searchList.remove("title")
        }

        viewModel.descriptionCheck.observe(this) {
            if (it) searchList.add("description") else searchList.remove("description")
        }

        viewModel.contentCheck.observe(this) {
            if (it) searchList.add("content") else searchList.remove("content")
        }

        Log.d("xxxxxxx", "onCreate: $searchIn")

        binding.buttonApply.setOnClickListener {
            searchIn = ""
            searchList.forEach {
                searchIn += "$it,"
            }
            Log.d("xxxxxxx", "onCreate: $searchIn")
            val intent = Intent().apply {
                putExtra(Constants.IntentConstants.SEARCH_IN, searchIn)
            }
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}