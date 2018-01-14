package com.lyricaloriginal.mnistandroid.recoginitions

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.contrib.android.TensorFlowInferenceInterface

/**
 * Created by LyricalMaestro0 on 2018/01/14.
 */
class ImageClassifier {

    private var intValues: IntArray
    private var inputFloats: FloatArray
    private var outputFloats: FloatArray

    private var inferenceInterface: TensorFlowInferenceInterface

    constructor(context: Context) {
        inferenceInterface = TensorFlowInferenceInterface(context.assets, "modeldata.pb")
        intValues = IntArray(28 * 28, { 0 })
        inputFloats = FloatArray(28 * 28, { 0.0f })
        outputFloats = FloatArray(10, { 0.0f })
    }

    fun classify(inputImage: Bitmap): Int {
        //  入力画像の前処理
        inputImage.getPixels(intValues, 0, inputImage.width, 0, 0, inputImage.width, inputImage.height)
        for (index in intValues.indices) {
            val r = (intValues[index] shr 16) and 0xFF
            val g = (intValues[index] shr 8) and 0xFF
            val b = (intValues[index]) and 0xFF
            inputFloats[index] = (r + g + b).toFloat() / 3.0f
        }

        //  予測
        inferenceInterface.feed("dense_1_input", inputFloats, 1, 28 * 28)
        inferenceInterface.run(arrayOf("output_node0"))
        inferenceInterface.fetch("output_node0", outputFloats)

        //  確信度が一番赤いindexを「予測する数字」とする。
        var minConfidence = Float.MIN_VALUE
        var minValue = -1
        for (index in outputFloats.indices){
            val confidence = outputFloats[index]
            if(minConfidence < confidence){
                minConfidence = confidence
                minValue = index
            }
        }
       return minValue
    }

    fun close(){
        inferenceInterface.close()
    }

}