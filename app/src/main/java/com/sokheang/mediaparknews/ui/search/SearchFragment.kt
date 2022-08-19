package com.sokheang.mediaparknews.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sokheang.mediaparknews.databinding.FragmentSearchBinding
import com.sokheang.mediaparknews.ui.article_filter.ArticleFilterActivity

class SearchFragment : Fragment() {

private var _binding: FragmentSearchBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    if(_binding == null) {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textNotifications
        searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.buttonFilter.setOnClickListener {
            val intent = Intent(context, ArticleFilterActivity::class.java)
            startActivity(intent)
        }
    }
    return binding.root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}