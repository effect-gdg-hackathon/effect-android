package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityBookmarkBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostsData

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private val postAdapter by lazy {
        PostItemsRecyclerViewAdapter(Category.LIFE, values = PostsData.lifeData!!.posts!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark)
        binding.uploadRecyclerview.adapter = postAdapter
    }
}