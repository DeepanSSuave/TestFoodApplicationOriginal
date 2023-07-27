package com.example.testfoodapplication.testfoodapplication.view.settings

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testfoodapplication.R

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImagesHolder>() {

    private var imagesList: List<ByteArray?> = emptyList()

    fun setImages(imagesList: List<ByteArray?>) {
        this.imagesList = imagesList
        this.notifyDataSetChanged()
    }

    inner class ImagesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.selectedImage)
        fun setImage(byte: ByteArray?) {
            val bitmap = byte?.let { BitmapFactory.decodeByteArray(it,0, it.size) }
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_setting_image, parent, false)
        return ImagesHolder(view)
    }

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.setImage(imagesList[position])
    }

    override fun getItemCount(): Int = imagesList.size
}