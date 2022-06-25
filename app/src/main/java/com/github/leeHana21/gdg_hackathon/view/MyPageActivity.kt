package com.github.leeHana21.gdg_hackathon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMypageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)
        binding.apply {
            mypageMyEffectLayout.setOnClickListener {
                startActivity(Intent(this@MyPageActivity,MyEffectActivity::class.java))
            }
            mypageBookmarkLayout.setOnClickListener {
                startActivity(Intent(this@MyPageActivity,BookmarkActivity::class.java))
            }
        }
    }
}