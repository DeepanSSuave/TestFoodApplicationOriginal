package com.example.testfoodapplication.testfoodapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.testfoodapplication.R
import com.example.testfoodapplication.testfoodapplication.model.db.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var imagesAdapter: ImageAdapter

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var database: AppDatabase

//    private val path: String =
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        imagesAdapter = ImageAdapter()
        splashViewPager.adapter = imagesAdapter

        lifecycleScope.launch {
            val jsonString = database.userDao().getAll()
            val uris = mutableListOf<Uri>()
            jsonString.forEach {
                uris.add(Uri.parse("$it"))
//                Uri.fromFile( File())
//                content://com.android.providers.downloads.documents/document/msf%3A1000000034
//                content://com.android.providers.media.documents/document/image%3A1000000037
            }
            uris.let { imagesAdapter.setImageDetails(it.toList()) }
        }

//        val imageURIs = sharedPreferences.getString("imageUris", "")
//        val listOfData = arrayListOf<ByteArray>()
//        imageURIs?.split(',')?.forEach {
//            listOfData.add(Base64.decode(it, Base64.DEFAULT))
//        }
//        val imgByteArray = imageURIs?.toByteArray()

//        val byteArray: ByteArray = Base64.decode(imageURIs, Base64.DEFAULT)
//        val byteArrayList : List<ByteArray?> = listOf(imgByteArray)
//        val imgBytes = imageURIs?.toByteArray()


        lifecycleScope.launch(Dispatchers.Main) {
            delay(3000)
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this@SplashActivity.finish()
        }
//
//        Handler().postDelayed( {
//            val i = Intent(this@SplashActivity, MainFragment::class.java)
//            startActivity(i)
//            finish()
//        }, 3000)
//        val splashImageView: ImageView = findViewById(R.id.splashImageView)
//        val imageUri: String? = sharedPreferences.getString("splash_image_uri", null)

//        if (imageUri != null) {
//            Picasso.get().load(imageUri).into(splashImageView)
//        }

//        Handler().postDelayed({
//            val intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, SPLASH_DELAY)
    }

    inner class ImageAdapter : PagerAdapter() {

        private var imageInformation : List<Uri> = emptyList()

        fun setImageDetails(imageInformation: List<Uri>) {
            this.imageInformation = imageInformation
            notifyDataSetChanged()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val contentView =this@SplashActivity.layoutInflater.inflate(
                R.layout.view_pager_item,
                container,
                false
            )
            imageInformation.forEach {
//                val inputStream = contentResolver.openInputStream(it)
//                val bitmap = BitmapFactory.decodeStream(inputStream)
//                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
//                val bitmap = decodeByteArray(it, 0, imageInformation.size)
//                contentView.findViewById<ImageView>(R.id.viewPagerItem).setImageURI(it)
                Glide.with(contentView.context)
                .load(it)
                .placeholder(R.drawable.food_splash)
                .into(contentView.findViewById(R.id.viewPagerItem))
            }



            container.addView(contentView)
            return contentView
        }

        override fun getCount(): Int {
            return imageInformation.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View?
            container.removeView(view)
        }
    }

}