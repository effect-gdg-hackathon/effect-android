package com.github.leeHana21.gdg_hackathon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityDetailBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostsData
import com.github.leeHana21.gdg_hackathon.view.viewModel.DetailViewModel
import com.github.leeHana21.gdg_hackathon.view.viewModel.MainViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private val viewModel = DetailViewModel()
    private val postsAdapter by lazy {
        PostItemsRecyclerViewAdapter(Category.POPULAR, PostsData.popularData!!.posts!!)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        getDataFromNetwork()
        initView()
        setUpObserver()
    }

    private fun getDataFromNetwork(){
        viewModel.getPostDetail(intent.getStringExtra("postId") ?: "1234" )
    }

    private fun setUpObserver(){
        viewModel.postDetailLiveData.observe(this){
            binding.apply {
                detailCategory.text = "생활"
                detailTitleTxt.text = it.title
                detailContent.text = "상사에게는 상표를 가리고 따라드려요 ~~~ !!!"
                Glide.with(detailThumbnailImage.context)
                    .load(it.imageUrl)
                    .into(detailThumbnailImage)
            }

        }
    }
    private fun initView(){
        binding.apply {
            detailTotalRecyclerview.adapter = postsAdapter
            detailEffectSpreadBtn.setOnClickListener {
                startActivity(Intent(this@DetailActivity,UploadActivity::class.java))
            }
        }
    }
}