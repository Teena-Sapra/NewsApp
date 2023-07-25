package com.tsapra.newsapp.ui2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsapra.newsapp.MainActivity
import com.tsapra.newsapp.R
import com.tsapra.newsapp.ReadActivity
import com.tsapra.newsapp.adapter.NewsAdapter
import com.tsapra.newsapp.utils.Resource
import com.tsapra.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.rv

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel:NewsViewModel
    lateinit var newsadapter:NewsAdapter

    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        setupRV()

        newsadapter.setOnItemClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{
            when(it){
                is Resource.Success ->{
                    it.data?.let{
                        newsadapter.differ.submitList(it.articles)
                    }
                }

                is Resource.Error ->{
                    it.message?.let{
                        Toast.makeText(activity, "${it.toString()}",Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {}
            }
        })
    }

    private fun setupRV() {
        newsadapter= NewsAdapter(requireActivity(), "FALSE")
        rv.apply{
            adapter=newsadapter
            layoutManager=LinearLayoutManager(activity)

        }
    }

}