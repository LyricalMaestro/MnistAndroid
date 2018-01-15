package com.lyricaloriginal.mnistandroid

import android.content.res.AssetManager
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * Created by LyricalMaestro0 on 2018/01/15.
 */
fun copyDirectory(assetManager: AssetManager, fromFilter : String, to: File) {
    if (!to.exists()) {
        to.mkdirs()
    }

    var fromFilenames = assetManager.list(fromFilter)
    if (fromFilenames.size > 0) {
        for (fromFilename in fromFilenames) {
            val toFile = File(to, fromFilename)
            assetManager.open(fromFilter + "/" + fromFilename).use {
                copyFile(it, toFile)
            }
        }
    }
}

fun copyFile(from : InputStream, to :File){
    var len : Int = 0
    var buf : ByteArray = kotlin.ByteArray(256)
    FileOutputStream(to).use{
        len = from.read(buf, 0, 256)
        while(len != -1){
            it.write(buf, 0, len)
            len = from.read(buf, 0, 256)
        }
    }
}