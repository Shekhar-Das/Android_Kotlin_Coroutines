package com.das.bd.android_kotlin_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Count dispatcher
        CoroutineScope(Main).launch {
            Log.i("MyTag","Calculation started ....")

            val stockOne = async(IO) { getStockOne() }
            val stockTwo = async(IO) { getStockTwo() }

            val  total = stockOne.await()+stockTwo.await()
            Toast.makeText(applicationContext , "TOtal us $total" , Toast.LENGTH_LONG).show()
            //Log.i("myTag",)
        }

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

        btnDownloadUserData.setOnClickListener {
            //may the screen will be fezzes up
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main){
            //    Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }

            // Set delay for function
            kotlinx.coroutines.delay(3000)

        }
    }

    private suspend fun getStockOne() : Int{
        delay(10000)
        Log.i("MyTag","Stock 1 returned")
        return 55000
    }
    private suspend fun getStockTwo() : Int{
        delay(8000)
        Log.i("MyTag","Stock 1 returned")
        return 55000
    }
}
