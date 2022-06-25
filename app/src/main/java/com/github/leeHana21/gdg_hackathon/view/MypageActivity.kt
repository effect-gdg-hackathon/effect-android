package com.github.leeHana21.gdg_hackathon.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.leeHana21.gdg_hackathon.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.mypageMyEffectLayout.setOnClickListener {
            val intent = Intent(this, MyEffectActivity::class.java)
            val bundle = Bundle()

//            bundle.putString("", )

            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.mypageBookmarkLayout.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            val bundle = Bundle()

//            bundle.putString("", )

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}