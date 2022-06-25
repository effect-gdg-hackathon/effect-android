package com.github.leeHana21.gdg_hackathon.view

import android.Manifest
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding

    // 사진 받기 위한 권한
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE)

    private val checkPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                if (!it.value) {
                    Toast.makeText(applicationContext, "권한 동의가 필요합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission.launch(permissionList)
        setUpView()
    }

    private fun setUpView() {
        // 카테고리 spinner
        resources?.getStringArray(R.array.category_array)?.let {
            binding.uploadCategorySpinner.adapter =
                ArrayAdapter(this, R.layout.spinner_dropdwon_item, it)
        }

        binding.uploadDeviceImageInput.setOnClickListener {
            readImage.launch("image/*")
        }
    }

    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.uploadImageView.setImageURI(uri)
    }
}