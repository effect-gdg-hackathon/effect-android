package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.leeHana21.gdg_hackathon.databinding.ActivityBookmarkBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.view.ViewModel.MainViewModel

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {

    }


    private fun getDataFromRemote()= with(mainViewModel){
        getBookMarkPosts(Category.POPULAR.categoryName)
    }
}