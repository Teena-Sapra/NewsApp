package com.tsapra.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.tsapra.newsapp.repository.NewsRepository
import com.tsapra.newsapp.ui.theme.NewsAppTheme
import com.tsapra.newsapp.viewModel.NewsViewModel
import com.tsapra.newsapp.viewModel.NewsViewModelProvider
import androidx.lifecycle.ViewModelProvider
import com.tsapra.newsapp.ui2.BreakingNewsFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit{
            setReorderingAllowed(true)
            add<BreakingNewsFragment>(R.id.fragmentContainerView)
        }
        val newsrepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProvider(newsrepository)
        viewModel= ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


    }


}