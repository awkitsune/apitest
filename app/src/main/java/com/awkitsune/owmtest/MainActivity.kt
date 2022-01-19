package com.awkitsune.owmtest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URI
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    var job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch{
            val fox = Get.GetRandomFox()

            var image: Bitmap?
            val imageView = findViewById<ImageView>(R.id.imageFox)
            val link = findViewById<TextView>(R.id.textViewFoxLink)

            try {
                val `in` = java.net.URL(fox?.image).openStream()
                image = BitmapFactory.decodeStream(`in`)

                imageView.post {
                    imageView.setImageBitmap(image)
                }
            }
            finally {

            }
            link.text = fox?.link
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}