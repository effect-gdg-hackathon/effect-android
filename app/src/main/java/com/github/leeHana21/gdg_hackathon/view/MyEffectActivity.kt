package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMyEffectBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostsData

class MyEffectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyEffectBinding
    private val postAdapter by lazy {
        PostItemsRecyclerViewAdapter(Category.INTERVIEW, values = PostsData.interviewData!!.posts!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_effect)
        binding.myeffectRecyclerview.adapter = postAdapter
    }
}