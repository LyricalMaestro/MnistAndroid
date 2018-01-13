package com.lyricaloriginal.mnistandroid.recoginitions

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.contrib.android.TensorFlowInferenceInterface

/**
 * Created by LyricalMaestro0 on 2018/01/14.
 */
class ImageClassifier{

    private var inferenceInterface : TensorFlowInferenceInterface

    constructor(context : Context) {
        inferenceInterface = TensorFlowInferenceInterface(context.assets, "modelname")
    }

    fun classify(inputImage : Bitmap) : Int{
        return -1
    }

}