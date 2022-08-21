package com.sokheang.mediaparknews.ui.search

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokheang.mediaparknews.MainActivity
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.FragmentSearchBinding
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.room.data.SearchHistory
import com.sokheang.mediaparknews.ui.article_filter.ArticleFilterActivity
import com.sokheang.mediaparknews.ui.news.adapter.ArticleListAdapter
import com.sokheang.mediaparknews.ui.search.adapter.SearchHistoryAdapter
import com.sokheang.mediaparknews.utils.Constants
import com.sokheang.mediaparknews.utils.views.BottomSheetSortBy
import javax.inject.Inject


class SearchFragment : Fragment() {

private var _binding: FragmentSearchBinding? = null

    @Inject
    lateinit var viewModel: SearchViewModel

    private val binding get() = _binding!!
    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter
    private lateinit var bottomSheetSortBy: BottomSheetSortBy

    private val articleList: ArrayList<ArticleListResponse.Article> = arrayListOf()
    private val searchHistoryList: ArrayList<SearchHistory> = arrayListOf()

    private var querySearch = "None"
    private var fromPublishDate = ""
    private var toPublishDate = ""
    private var searchIn = "${Constants.SearchInConstants.TITLE}, ${Constants.SearchInConstants.DESCRIPTION}"
    private var sortBy = Constants.SortByConstants.PUBLISH_AT

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

            bottomSheetSortBy = BottomSheetSortBy(requireContext(), {
                //On sort by upload date selected
                sortBy = Constants.SortByConstants.PUBLISH_AT
                searchArticles()
            },{
                //On sort by upload relevance selected
                sortBy = Constants.SortByConstants.RELEVANCE
                searchArticles()
            })

            binding.buttonSort.setOnClickListener {
                bottomSheetSortBy.show()
            }

            articleListAdapter = ArticleListAdapter(articleList)
            binding.recyclerViewSearchArticle.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                itemAnimator = DefaultItemAnimator()
                adapter = articleListAdapter
            }

            searchHistoryAdapter = SearchHistoryAdapter(searchHistoryList, ::onHistoryItemClick)
            binding.recyclerViewSearchHistory.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = searchHistoryAdapter
            }
            viewModel.getAllHistory().observe(
                viewLifecycleOwner
            ) {
                searchHistoryList.clear()
                searchHistoryList.addAll(it)
                searchHistoryAdapter.notifyDataSetChanged()
            }

            //binding.editTextQuery.addTextChangedListener(searchTextWatcher)
            binding.editTextQuery.setOnEditorActionListener { textView, actionId, _ ->
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    querySearch = textView.text.toString().trim()
                    viewModel.saveHistory(listOf(SearchHistory(0,querySearch)))
                    searchArticles()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
    }
    return binding.root
  }


    private fun searchArticles() {
        viewModel.isLoading.value = true
        viewModel.isLoadFail.value = false
        viewModel.isZeroItemsLoaded.value = false
        viewModel.showResult.value = true

        viewModel.getArticleList(
            Constants.ArticleTypeConstants.SEARCH,
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
                    viewModel.isLoadFail.value = false
                    articleList.addAll(it.articles)
                    viewModel.isZeroItemsLoaded.value = articleList.isEmpty()
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

    private fun onHistoryItemClick(searchHistory: SearchHistory) {
        binding.editTextQuery.setText(searchHistory.history)
        querySearch = searchHistory.history
        viewModel.saveHistory(listOf(SearchHistory(0,querySearch))) //Save again to make keep it first in list
        searchArticles()
    }

    //For search when typing
//    private var timer: Timer? = null
//    private val searchTextWatcher: TextWatcher = object : TextWatcher {
//        override fun afterTextChanged(arg0: Editable) {
//            // user typed: start the timer
//            timer = Timer()
//            timer!!.schedule(object : TimerTask() {
//                override fun run() {
//                    val handler = Handler(Looper.getMainLooper())
//                    handler.post {
//                        querySearch = arg0.toString().trim()
//                        searchArticles()
//                    }
//                }
//            }, 600) // 600ms delay before the timer executes the „run“ method from TimerTask
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//            // nothing to do here
//        }
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//            // user is typing: reset already started timer (if existing)
//            if (timer != null) {
//                timer!!.cancel()
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}