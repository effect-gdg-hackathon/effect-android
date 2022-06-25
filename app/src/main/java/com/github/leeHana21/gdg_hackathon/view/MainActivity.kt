package com.github.leeHana21.gdg_hackathon.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMainBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.PostDetail
import com.github.leeHana21.gdg_hackathon.view.ViewModel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel = MainViewModel()
    private var popularData : Array<PostDetail> ? = null
    private var lifeData : Array<PostDetail> ? = null
    private var interviewData : Array<PostDetail> ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getDataFromRemote()
        initView()
    }
    private fun setUpData(){
    }

    private fun getDataFromRemote()= with(mainViewModel){
        getCategoryPosts(Category.POPULAR.categoryName)
    }

    private fun initView()= with(binding) {
        activity = this@MainActivity

        tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    1 ->{

                    }
                    2 -> {

                    }
                    else -> {

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })

        val list = listOf(PostDetail(title = "타이틀",
            "1",
            creatorId = "1","https://725f-118-129-228-11.ngrok.io/images/0bb18f8e305ca6ae-1656160445486.png","1"),PostDetail(title = "타이틀",
            "1",
            creatorId = "1","https://725f-118-129-228-11.ngrok.io/images/0bb18f8e305ca6ae-1656160445486.png","1"),PostDetail(title = "타이틀",
            "1",
            creatorId = "1","https://725f-118-129-228-11.ngrok.io/images/0bb18f8e305ca6ae-1656160445486.png","1"),PostDetail(title = "타이틀",
            "1",
            creatorId = "1","https://725f-118-129-228-11.ngrok.io/images/0bb18f8e305ca6ae-1656160445486.png","1"),PostDetail(title = "타이틀",
            "1",
            creatorId = "1","https://725f-118-129-228-11.ngrok.io/images/0bb18f8e305ca6ae-1656160445486.png","1"))
        val postsAdapter = PostItemsRecyclerViewAdapter(Category.POPULAR,list)
        rvList.adapter = postsAdapter

    }

    private fun setUpObserver()= with(mainViewModel){
        postsLiveData.observe(this@MainActivity){

        }
    }

    fun onAddPostClick(){
        showToast(this, "이동 이동")
        // startActivity()
    }

    fun onClickHome(){
        showToast(this, "이동 이동")

        // startActivity()
    }

    fun onClickProfile(){
        showToast(this, "프래그먼트 전환 이동 이동")
        // startActivity()

    }

    fun Context.showToast(context: Context, text : String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }
}

