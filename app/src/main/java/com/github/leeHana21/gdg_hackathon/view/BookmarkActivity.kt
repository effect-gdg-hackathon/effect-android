package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.FragmentBookmarkBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostsData

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: FragmentBookmarkBinding
    private val postAdapter by lazy {
        PostItemsRecyclerViewAdapter(Category.LIFE, values = PostsData.lifeData!!.posts!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_bookmark)
        binding.uploadRecyclerview.adapter = postAdapter
    }
}