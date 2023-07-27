package com.example.testfoodapplication.testfoodapplication.view.settings

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.JsonToken
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testfoodapplication.databinding.FragmentSettingBinding
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import com.example.testfoodapplication.testfoodapplication.model.Image
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment() {

    lateinit var imagesAdapter: ImagesAdapter
    lateinit var settingBinding: FragmentSettingBinding
    lateinit var imagesUri: List<ByteArray?>

    @Inject
    lateinit var database: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingBinding = FragmentSettingBinding.inflate(inflater, container, false)

        imagesAdapter = ImagesAdapter()

        settingBinding.recyclerViewImg.apply {
            adapter = imagesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
            setHasFixedSize(true)
        }
        settingBinding.selectImages.setOnClickListener {
            imagePicker.launch("image/*")
        }

        settingBinding.saveImages.setOnClickListener {

        }
        return settingBinding.root
    }

    private val imagePicker = registerForActivityResult(
        ActivityResultContracts.GetMultipleContents()
    ) { uris ->

        lifecycleScope.launch {
            uris.forEach {
                database.userDao().insert(Image(null, it.toString()))
            }
        }


        val byteArrayList: List<ByteArray?>? = context?.let { uriListToByteArrayList(it, uris) }

        if (byteArrayList != null) {
            imagesAdapter.setImages(byteArrayList)
        }
        imagesUri = byteArrayList as List<ByteArray?>

    }

    @Throws(IOException::class)
    fun uriListToByteArrayList(context: Context, uriList: List<Uri>): List<ByteArray?> {
        val contentResolver: ContentResolver = context.contentResolver
        val byteArrayList = mutableListOf<ByteArray?>()

        for (uri in uriList) {
            val inputStream = contentResolver.openInputStream(uri)

            var byteArray: ByteArray? = null
            if (inputStream != null) {
                try {
                    val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                    byteArray = byteArrayOutputStream.toByteArray()
                    byteArrayOutputStream.close()
                } catch (e: Exception) {
                    Log.e("UriToByteArray", "Error converting Uri to byte array: ${e.message}")
                } finally {
                    inputStream.close()
                }
            }

            byteArrayList.add(byteArray)
        }

        return byteArrayList
    }
}