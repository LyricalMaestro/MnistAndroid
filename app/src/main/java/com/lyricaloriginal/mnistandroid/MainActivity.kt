package com.lyricaloriginal.mnistandroid

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.lyricaloriginal.mnistandroid.recoginitions.ImageClassifier

class MainActivity : AppCompatActivity() {

    var imageClassifier : ImageClassifier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.classify_btn1).setOnClickListener{
            val bitmap = BitmapFactory.decodeStream(assets.open("sampleimages/test0.jpg"))
            val number = imageClassifier?.classify(bitmap)
            Toast.makeText(this, "値は" + number, Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.classify_btn2).setOnClickListener{
            val bitmap = BitmapFactory.decodeStream(assets.open("sampleimages/test4.jpg"))
            val number = imageClassifier?.classify(bitmap)
            Toast.makeText(this, "値は" + number, Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        imageClassifier = ImageClassifier(this)
    }

    override fun onPause() {
        super.onPause()
        imageClassifier?.close()
    }
}
