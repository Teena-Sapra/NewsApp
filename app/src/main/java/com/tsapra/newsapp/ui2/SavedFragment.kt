package com.tsapra.newsapp.ui2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsapra.newsapp.MainActivity
import com.tsapra.newsapp.R
import com.tsapra.newsapp.adapter.NewsAdapter
import com.tsapra.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.rv2


class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsadapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRV()
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsadapter.differ.submitList(articles)
        })
        newsadapter.setOnItemClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }

    }
    private fun setupRV() {
        newsadapter= NewsAdapter(requireActivity(), "FALSE")
        rv2.apply{
            adapter=newsadapter
            layoutManager= LinearLayoutManager(activity)

        }
    }

}