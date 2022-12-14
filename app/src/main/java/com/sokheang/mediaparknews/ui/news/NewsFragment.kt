package com.sokheang.mediaparknews.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokheang.mediaparknews.MainActivity
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.FragmentNewsBinding
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.ui.news.adapter.ArticleListAdapter
import com.sokheang.mediaparknews.utils.Constants
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    @Inject
    lateinit var viewModel: NewsViewModel

    private val binding get() = _binding!!
    private lateinit var articleListAdapter: ArticleListAdapter
    private val articleList: ArrayList<ArticleListResponse.Article> = arrayListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            (context.application as MediaparkNewsApp).getApplicationComponent().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if(_binding == null) { //init binding incase it null
            _binding = FragmentNewsBinding.inflate(inflater, container, false)

            articleListAdapter = ArticleListAdapter(articleList)

            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel

            setUpArticleRecyclerView()

            getArticleTopHeadLineList()
        }
        return binding.root
    }

    //Get top head line list
    private fun getArticleTopHeadLineList() {
        viewModel.getArticleList(Constants.ArticleTypeConstants.TOP_HEAD_LINE)
            .observe(viewLifecycleOwner) {
                viewModel.isLoading.value = false
                //Handle when recieve data
                if (it != null) {
                    viewModel.isZeroItemsLoaded.value = articleList.isNotEmpty()
                    articleList.addAll(it.articles)
                    articleListAdapter.notifyDataSetChanged()
                } else {
                    viewModel.isLoadFail.value = true
                }
            }
    }

    private fun setUpArticleRecyclerView() {
        binding.recyclerViewArticle.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = articleListAdapter
        }
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}