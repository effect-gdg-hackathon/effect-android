package com.github.leeHana21.gdg_hackathon.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivitySplashBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.view.ViewModel.MainViewModel

class SplashActivity : AppCompatActivity() {
    private var viewModel = MainViewModel()
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        getDataFromRemote()
        setUpObserver()
    }

    private fun getDataFromRemote(){
        viewModel.apply {
            getCategoryProjectAll()
        }
    }
    private fun setUpObserver(){
        viewModel.postAllLiveData.observe(this){
            val intent = Intent()
            intent.putExtra(Category.POPULAR.categoryName, it.first.posts)
            intent.putExtra(Category.LIFE.categoryName, it.second.posts)
            intent.putExtra(Category.INTERVIEW.categoryName, it.third.posts)
            startActivity(intent)
            finish()
        }
    }
}