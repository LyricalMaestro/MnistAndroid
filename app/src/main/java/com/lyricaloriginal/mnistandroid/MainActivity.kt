package com.lyricaloriginal.mnistandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import com.lyricaloriginal.mnistandroid.recoginitions.ImageClassifier
import java.io.File

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener{

    var imageClassifier : ImageClassifier? = null
    var imageFiles : List<File>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            copyImage()
        }
        imageFiles = getExternalFilesDir(Environment.DIRECTORY_DCIM).listFiles().asList()

        var gridView : GridView = findViewById(R.id.target_image_grid_view)
        gridView.adapter = MnistAdapter(this, imageFiles)
        gridView.onItemClickListener = this
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val imageFile = imageFiles!![p2]
        val inputImage = BitmapFactory.decodeFile(imageFile.absolutePath)
        val category = imageClassifier!!.classify(inputImage)
        updateUi(inputImage, category)
    }

    override fun onResume() {
        super.onResume()
        imageClassifier = ImageClassifier(this)
    }

    override fun onPause() {
        super.onPause()
        imageClassifier?.close()
    }

    private fun copyImage(){
        val outputDir = getExternalFilesDir(Environment.DIRECTORY_DCIM)

        copyDirectory(assets, "sampleimages", outputDir)
    }

    private fun updateUi(selectedImage : Bitmap, category : Int){
        val imageView : ImageView = findViewById(R.id.selected_image_view)
        imageView.setImageBitmap(selectedImage)

        val textView : TextView = findViewById(R.id.result_text_view)
        textView.setText("answer is " + category)
    }
}
