package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.FragmentMyeffectBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostsData

class MyEffectActivity : AppCompatActivity() {
    private lateinit var binding: FragmentMyeffectBinding
    private val postAdapter by lazy {
        PostItemsRecyclerViewAdapter(Category.INTERVIEW, values = PostsData.interviewData!!.posts!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_myeffect)
        binding.myeffectRecyclerview.adapter = postAdapter
    }
}