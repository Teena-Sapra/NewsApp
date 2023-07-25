package com.tsapra.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tsapra.newsapp.R
import com.tsapra.newsapp.data.entities.Article
import kotlinx.android.synthetic.main.single_item.view.DescEdit
import kotlinx.android.synthetic.main.single_item.view.imageView
import kotlinx.android.synthetic.main.single_item.view.publishDateEdit
import kotlinx.android.synthetic.main.single_item.view.readBtn
import kotlinx.android.synthetic.main.single_item.view.titleEdit

class NewsAdapter(var require:FragmentActivity, var check:String):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView:View ):RecyclerView.ViewHolder(itemView){

    }

    private val diffCallback=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }

    val differ=AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load(article.urlToImage).into(imageView)
            titleEdit.text=article.title
            DescEdit.text=article.description
            publishDateEdit.text=article.publishedAt
            setOnClickListener {
                onItemClickListener?.let{
                    it(article)
                }
            }
            readBtn.setOnClickListener{
                onItemClickListener?.let{
                    it(article)
                }
            }
        }
    }
    private var onItemClickListener:((Article)-> Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }
}