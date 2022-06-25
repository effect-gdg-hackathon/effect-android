package com.github.leeHana21.gdg_hackathon.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.leeHana21.gdg_hackathon.R
import com.github.leeHana21.gdg_hackathon.databinding.ActivityUploadBinding
import com.github.leeHana21.gdg_hackathon.entity.Category
import com.github.leeHana21.gdg_hackathon.entity.UploadRequest
import com.github.leeHana21.gdg_hackathon.view.viewModel.UploadViewModel
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var file : File
    private var realPath = ""
    private val viewModel = UploadViewModel()
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
        setUpObserver()
    }

    private fun setUpView() {
        binding.empty = true

        // 카테고리 spinner
        resources?.getStringArray(R.array.category_array)?.let {
            binding.uploadCategorySpinner.adapter =
                ArrayAdapter(this, R.layout.spinner_dropdwon_item, it)
        }

        binding.detailEffectSpreadBtn.setOnClickListener {
            readImage.launch("image/*")
        }

        binding.searchCartBtn.setOnClickListener {
            onClickUpload()
        }
    }
    private fun setUpObserver(){
        viewModel.uploadResponse.observe(this){
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("postId",it.postId)
            startActivity(intent)
        }
    }

    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.uploadImageBtn.setImageURI(uri)
        binding.empty = false
        realPath = getRealPathFromURI(uri) ?: "-"
        Log.d("uri", "uri: $realPath")
    }

    private fun getRealPathFromURI(contentUri : Uri) : String? {
        if (contentUri.path?.startsWith("/storage") == true) {
            return contentUri.path!!
        }

        val id = DocumentsContract.getDocumentId(contentUri).split(":")[1]
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id
        val cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            val columnIndex = cursor?.getColumnIndex(columns[0])
            if (cursor?.moveToFirst() == true) {
                return cursor.getString(columnIndex!!)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun onClickUpload(){
        file = File(realPath)
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageData = MultipartBody.Part.createFormData("image", file.name, requestBody)
        val spinnerItem = when(binding.uploadCategorySpinner.selectedItem.toString()){
            "생활" -> Category.LIFE.categoryName
            else -> Category.INTERVIEW.categoryName
        }
        val uploadRq = UploadRequest(category = spinnerItem,
            title = binding.uploadTitleEditTxt.text.toString(),
            binding.etContents.text.toString(),
            parentPostId = null)
        val gson = Gson()
        val jsonString = gson.toJson(uploadRq)
        val requestTitle = RequestBody.create("text/plain".toMediaTypeOrNull(), jsonString)

        viewModel.postEffect("1",imageData, requestTitle)
    }
}