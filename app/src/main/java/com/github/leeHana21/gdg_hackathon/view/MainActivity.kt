package com.github.leeHana21.gdg_hackathon.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMainBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.view.viewModel.MainViewModel
import com.github.leeHana21.gdg_hackathon.entity.PostsData
import com.github.leeHana21.gdg_hackathon.entity.PostsResponse
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel = MainViewModel()
    private var popularData : PostsResponse ? = null
    private var lifeData : PostsResponse ? = null
    private var interviewData : PostsResponse ? = null
    private lateinit var popularAdapter : PostItemsRecyclerViewAdapter
    private lateinit var lifeAdapter : PostItemsRecyclerViewAdapter
    private lateinit var interviewAdapter : PostItemsRecyclerViewAdapter
    private var tabPosition : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpData()
        initView()
    }
    private fun setUpData() {
        popularData = PostsData.popularData
        lifeData = PostsData.lifeData
        interviewData = PostsData.interviewData
        popularAdapter = PostItemsRecyclerViewAdapter(Category.POPULAR, popularData!!.posts!!.toList())
        lifeAdapter = PostItemsRecyclerViewAdapter(Category.LIFE, lifeData!!.posts!!.toList())
        interviewAdapter = PostItemsRecyclerViewAdapter(Category.INTERVIEW, interviewData!!.posts!!.toList())
    }

    private fun initView()= with(binding) {
        activity = this@MainActivity
        rvList.adapter = popularAdapter

        tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition = tab?.position ?: 0
                when(tab?.position){
                    1 -> {
                        binding.rvList.adapter = null
                        binding.rvList.adapter = lifeAdapter
                    }
                    2 -> {
                        binding.rvList.adapter = null
                        binding.rvList.adapter = interviewAdapter
                    }
                    else -> {
                        binding.rvList.adapter = null
                        binding.rvList.adapter = popularAdapter
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpObserver()= with(mainViewModel){
        postsLiveData.observe(this@MainActivity){

        }
    }

    fun onAddPostClick(){
        startActivity(Intent(this,UploadActivity::class.java))
    }

    fun onClickHome(){
        if(tabPosition != 0){
            binding.rvList.adapter = null
            binding.rvList.adapter = popularAdapter
            binding.tabMain.setScrollPosition(0,0f,true)
            binding.ivHome.setColorFilter(Color.parseColor("#6201FF"))
            binding.ivProfile.setColorFilter(Color.parseColor("#888888"))
        }
    }

    fun onClickProfile(){
        startActivity(Intent(this,MyPageActivity::class.java))

    }

    fun Context.showToast(context: Context, text : String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }
}

