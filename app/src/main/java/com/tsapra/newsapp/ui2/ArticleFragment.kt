package com.tsapra.newsapp.ui2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.tsapra.newsapp.MainActivity
import com.tsapra.newsapp.R
import com.tsapra.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.webView

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = requireArguments().getString("url")
        viewModel = (activity as MainActivity).viewModel
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url!!)
        }

    }
}