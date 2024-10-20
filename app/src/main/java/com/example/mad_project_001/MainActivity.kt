package com.example.mad_project_001

import android.graphics.Color
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topNum: TextView = findViewById<TextView?>(R.id.top_box)
        val bottomNum: TextView = findViewById(R.id.bottom_box)
        val startButton: Button = findViewById(R.id.start_button)
        val background: LinearLayout = findViewById(R.id.main)

        topNum.setOnClickListener {
            val randomNum = Random.nextInt(1,100)
            topNum.text = randomNum.toString()
            val result: Int = compare(topNum,bottomNum)
            if(result == 1){
                changeColor("#0dd925", background)
            }else{
                changeColor("#d90d0d",background)
            }
        }
        bottomNum.setOnClickListener{
            val randomNum = Random.nextInt(1,100)
            bottomNum.text = randomNum.toString()
            val result: Int = compare(bottomNum,topNum)
            if(result == 1){
                changeColor("#0dd925", background)
            }else{
                changeColor("#d90d0d",background)
            }
        }
    }
    private fun compare(view1: TextView, view2: TextView) : Int {
        var val1 = view1.text.toString()
        var val2 = view2.text.toString()
        if ( val1.isNotEmpty()) {val1 = val1.toInt().toString() }
        if ( val2.isNotEmpty()) {val2 = val2.toInt().toString() }
        return when {
            val1 > val2 -> 0
            val2 > val1 -> 1
            else -> 0
        }
    }
    private fun changeColor(color: String, background: LinearLayout){
        background.setBackgroundColor(Color.parseColor(color))
    }
}
