package com.tsapra.newsapp.ui2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsapra.newsapp.MainActivity
import com.tsapra.newsapp.R
import com.tsapra.newsapp.adapter.NewsAdapter
import com.tsapra.newsapp.utils.Resource
import com.tsapra.newsapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.rv2
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel:NewsViewModel
    lateinit var newsadapter:NewsAdapter

    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        setupRV()
        newsadapter.setOnItemClickListener {
            /*val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it.url)
            startActivity(intent)*/
            val fragmentManager = parentFragmentManager
            val bundle= bundleOf("url" to it.url)
            fragmentManager.commit{
                replace<ArticleFragment>(R.id.fragmentContainerView, args=bundle)
                setReorderingAllowed(true)
                addToBackStack("name")
            }
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
        rv2.apply{
            adapter=newsadapter
            layoutManager=LinearLayoutManager(activity)

        }
    }

}