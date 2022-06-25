package com.github.leeHana21.gdg_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMyeffectBinding
import com.github.leeHana21.gdg_hackathon.view.ViewModel.MainViewModel

class MyEffectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyeffectBinding
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyeffectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromRemote()
        setUpView()
    }

    private fun setUpView() {

    }

    private fun getDataFromRemote()= with(mainViewModel){
        getMyPosts("1")
    }
}