package com.sokheang.mediaparknews.ui.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sokheang.mediaparknews.MainActivity
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.FragmentNewsBinding
import com.sokheang.mediaparknews.models.ArticleListResponse
import com.sokheang.mediaparknews.ui.news.adapter.ArticleListAdapter
import javax.inject.Inject

class NewsFragment : Fragment() {

private var _binding: FragmentNewsBinding? = null

    @Inject
    lateinit var viewModel: NewsViewModel

    private val binding get() = _binding!!
    private lateinit var articleListAdapter: ArticleListAdapter
    private val articleList: ArrayList<ArticleListResponse.Article> = arrayListOf();

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            (context.application as MediaparkNewsApp).getApplicationComponent().inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if(_binding == null) {
            _binding = FragmentNewsBinding.inflate(inflater, container, false)

            val textView: TextView = binding.textDashboard
            viewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }

            articleListAdapter = ArticleListAdapter(articleList)
            binding.recyclerViewArticle.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                itemAnimator = DefaultItemAnimator()
                adapter = articleListAdapter
            }

            viewModel.getArticleList("top-headlines").observe(viewLifecycleOwner) {
                articleList.addAll(it.articles)
                articleListAdapter.notifyDataSetChanged()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}