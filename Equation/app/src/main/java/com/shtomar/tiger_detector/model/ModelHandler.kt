package com.shtomar.tiger_detector.model

import android.util.Log
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import org.tensorflow.lite.Interpreter

class ModelDownloader {

    companion object{
        suspend fun downloadModel() {
            val conditions = CustomModelDownloadConditions.Builder()
                .requireWifi()
                .build()
            FirebaseModelDownloader.getInstance()
                .getModel("Equation-model", DownloadType.LOCAL_MODEL, conditions)
                .addOnCompleteListener {
                    print("")
                    // Download complete. Depending on your app, you could enable the ML
                    // feature, or switch from the local model to the remote model, etc.

                    //Inference code
                    val interpreter = Interpreter(it.result.file!!)

                    val inputVal = FloatArray(1)
                    inputVal[0] = 10f
                    val output = Array(1) {
                        FloatArray(
                            1
                        )
                    }
                    interpreter.run(inputVal, output)
                    Log.d("EQUATION MODEL:::", "The output from the model for the input 10 is:: ${output[0][0]}")
                }
        }
    }
}