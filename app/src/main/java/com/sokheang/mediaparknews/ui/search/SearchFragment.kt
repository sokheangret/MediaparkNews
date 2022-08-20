package com.sokheang.mediaparknews.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokheang.mediaparknews.MainActivity
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.FragmentSearchBinding
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.ui.article_filter.ArticleFilterActivity
import com.sokheang.mediaparknews.ui.news.adapter.ArticleListAdapter
import com.sokheang.mediaparknews.utils.Constants
import javax.inject.Inject

class SearchFragment : Fragment() {

private var _binding: FragmentSearchBinding? = null

    @Inject
    lateinit var viewModel: SearchViewModel

    private val binding get() = _binding!!
    private lateinit var articleListAdapter: ArticleListAdapter
    private val articleList: ArrayList<ArticleListResponse.Article> = arrayListOf()

    var querySearch = "None"
    var fromPublishDate = ""
    var toPublishDate = ""
    var searchIn = "title,description"
    var sortBy = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            (context.application as MediaparkNewsApp).getApplicationComponent().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if(_binding == null) {
            _binding = FragmentSearchBinding.inflate(inflater, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel

            binding.buttonFilter.setOnClickListener {
                val intent = Intent(context, ArticleFilterActivity::class.java).apply {
                    putExtra(Constants.IntentConstants.FROM_PUBLISH_DATE, fromPublishDate)
                    putExtra(Constants.IntentConstants.TO_PUBLISH_DATE, toPublishDate)
                    putExtra(Constants.IntentConstants.SEARCH_IN, searchIn)
                }
                resultLauncher.launch(intent)
            }

            binding.buttonSort.setOnClickListener {
            }

            articleListAdapter = ArticleListAdapter(articleList)
            binding.recyclerViewSearchArticle.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                itemAnimator = DefaultItemAnimator()
                adapter = articleListAdapter
            }

            binding.editTextQuery.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(string: Editable?) {
                }

            })
    }
    return binding.root
  }

    private fun searchArticles() {
        viewModel.getArticleList(
            "search",
            querySearch,
            fromPublishDate,
            toPublishDate,
            searchIn,
            sortBy
        )
            .observe(viewLifecycleOwner) {
                viewModel.isLoading.value = false
                articleList.clear()
                if (it != null) {
                    viewModel.isZeroItemsLoaded.value = articleList.isNotEmpty()
                    articleList.addAll(it.articles)
                    articleListAdapter.notifyDataSetChanged()
                    viewModel.searchResultCount.value = "${it.totalArticles} news"
                } else {
                    viewModel.isLoadFail.value = true
                    viewModel.searchResultCount.value = "Search History"
                }
            }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent = result.data!!
            fromPublishDate = data.getStringExtra(Constants.IntentConstants.FROM_PUBLISH_DATE).toString()
            toPublishDate = data.getStringExtra(Constants.IntentConstants.TO_PUBLISH_DATE).toString()
            searchIn = data.getStringExtra(Constants.IntentConstants.SEARCH_IN).toString()

            searchArticles()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}