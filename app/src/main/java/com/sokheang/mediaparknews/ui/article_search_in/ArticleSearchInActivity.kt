package com.sokheang.mediaparknews.ui.article_search_in

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.ActivityArticleSearchInBinding
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

        binding.toolbar.textClear.visibility = View.VISIBLE
        binding.toolbar.textClear.setOnClickListener {
            clearFilter()
        }

        binding.toolbar.buttonBack.visibility = View.VISIBLE
        binding.toolbar.buttonBack.setOnClickListener {
            onBackPressed()
        }

        if(searchIn.contains(Constants.SearchInConstants.TITLE)) {
            viewModel.titleCheck.value = true
        }

        if(searchIn.contains(Constants.SearchInConstants.DESCRIPTION)) {
            viewModel.descriptionCheck.value = true
        }

        if(searchIn.contains(Constants.SearchInConstants.CONTENT)) {
            viewModel.contentCheck.value = true
        }

        viewModel.titleCheck.observe(this) {
            if (it) searchList.add(Constants.SearchInConstants.TITLE) else searchList.remove(Constants.SearchInConstants.TITLE)
        }

        viewModel.descriptionCheck.observe(this) {
            if (it) searchList.add(Constants.SearchInConstants.DESCRIPTION) else searchList.remove(Constants.SearchInConstants.DESCRIPTION)
        }

        viewModel.contentCheck.observe(this) {
            if (it) searchList.add(Constants.SearchInConstants.CONTENT) else searchList.remove(Constants.SearchInConstants.CONTENT)
        }

        binding.buttonApply.setOnClickListener {
            searchIn = ""
            searchList.forEach {
                searchIn += "$it,"
            }
            val intent = Intent().apply {
                putExtra(Constants.IntentConstants.SEARCH_IN, searchIn)
            }
            setResult(RESULT_OK,intent)
            finish()
        }
    }

    private fun clearFilter() {
        searchList.clear()
        viewModel.titleCheck.value = false
        viewModel.descriptionCheck.value = false
        viewModel.contentCheck.value = false
    }
}