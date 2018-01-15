package com.lyricaloriginal.mnistandroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import java.io.File

/**
 * Created by LyricalMaestro0 on 2018/01/15.
 */
class MnistAdapter(context: Context, objects: List<File>?) : ArrayAdapter<File>
    (context, 0, objects){

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null){
            view = inflater.inflate(R.layout.mnist_view, null)
        }
        val imageView : ImageView = view!!.findViewById(R.id.image_view)
        imageView.setImageBitmap(BitmapFactory.decodeFile(getItem(position).absolutePath))
        return view
    }
}