package com.example.mad_project_001

import android.graphics.Color
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Delay
import kotlin.concurrent.thread
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        var strikeCount: Int = 0;
        var scoreCount: Int = 0;
        val strikes: TextView = findViewById(R.id.strikes);
        val score: TextView = findViewById(R.id.score);
        val startText: TextView = findViewById(R.id.start_text);
        val topNum: TextView = findViewById(R.id.top_box);
        val bottomNum: TextView = findViewById(R.id.bottom_box);
        val startButton: Button = findViewById(R.id.start_button);
        val background: LinearLayout = findViewById(R.id.main);

        startButton.setOnClickListener{
            if(startButton.text.equals("Start")){
                setRandNums(topNum,bottomNum);
                startButton.text = "Restart";
                startText.text = "Tap the larger number";
            }else{
                restart(topNum,bottomNum,startButton,startText,strikes,score,background);
                scoreCount = 0;
                strikeCount=0;
            }
        }
        topNum.setOnClickListener {
            val result: Int = compare(topNum,bottomNum);
            if(result == 0){
                scoreCount++;
                score.text = "Score: $scoreCount"
                changeColor("#0dd925", background);
                score.setTextColor(Color.parseColor("#FFFDEF74"))
                strikes.setTextColor(Color.parseColor("#000000"))
            }else{
                strikeCount++;
                strikes.text = "Strikes: $strikeCount"
                strikes.setTextColor(Color.parseColor("#FFFDEF74"))
                score.setTextColor(Color.parseColor("#000000"))
                changeColor("#d90d0d",background);
            }
            setRandNums(topNum,bottomNum);
            checkWinLoseConditions(topNum,bottomNum,startButton,startText,strikes,score,background,scoreCount,strikeCount)
        }
        bottomNum.setOnClickListener{
            val result: Int = compare(bottomNum,topNum);
            if(result == 0){
                scoreCount++;
                score.text = "Score: $scoreCount"
                score.setTextColor(Color.parseColor("#FFFDEF74"))
                strikes.setTextColor(Color.parseColor("#000000"))
                changeColor("#0dd925", background);
            }else{
                strikeCount++;
                strikes.text = "Strikes: $strikeCount"
                strikes.setTextColor(Color.parseColor("#FFFDEF74"))
                score.setTextColor(Color.parseColor("#000000"))
                changeColor("#d90d0d",background);
            }
            setRandNums(topNum,bottomNum);
            checkWinLoseConditions(topNum,bottomNum,startButton,startText,strikes,score,background,scoreCount,strikeCount)
        }
    }
    private fun compare(view1: TextView, view2: TextView) : Int {
        val val1 = view1.text.toString().toIntOrNull() ?: 0
        val val2 = view2.text.toString().toIntOrNull() ?: 0

        return when {
            val1 > val2 -> 0
            val2 > val1 -> 1
            else -> 0
        }
    }
    private fun changeColor(color: String, background: LinearLayout){
        background.setBackgroundColor(Color.parseColor(color))
    }
    private fun setRandNums(view1: TextView, view2: TextView){
        val rand1 = Random.nextInt(0,100);
        val rand2 = Random.nextInt(0,100);

        view1.text = rand1.toString();
        view2.text = rand2.toString();
    }
    private fun restart(numView1:TextView,numView2:TextView,button:Button,startText:TextView,strikes:TextView,score:TextView,background: LinearLayout){
        numView1.text = "";
        numView2.text= "";
        button.text="Start";
        startText.text="Press start to play again";
        strikes.text="Strikes: " + 0
        score.text="Score: " + 0
        score.setTextColor(Color.BLACK)
        strikes.setTextColor(Color.BLACK)
        changeColor("#FFFDEF74",background)
    }

    private fun displayWin () {
        val winToast: Toast = Toast.makeText(this, "You Win :)", Toast.LENGTH_LONG)
        winToast.show();
    }
    private fun displayLose(){
            val loseToast: Toast = Toast.makeText(this,"You Lose :(",Toast.LENGTH_LONG)
            loseToast.show();
    }
    private fun checkWinLoseConditions(numView1:TextView,numView2:TextView,button:Button,
                                       startText:TextView,strikes:TextView,score:TextView,
                                       background: LinearLayout,scoreCount:Int,strikeCount:Int,) {
        if (scoreCount == 10) {
            score.setTextColor(Color.parseColor("#FFFDEF74"))
            displayWin()
            restart(numView1, numView2, button, startText, strikes, score, background)
            score.setTextColor(Color.parseColor("#3dfc03"))
        }
        if (strikeCount == 3) {
            strikes.setTextColor(Color.parseColor("#FFFDEF74"))
            displayLose()
            restart(numView1, numView2, button, startText, strikes, score, background)
            strikes.setTextColor(Color.parseColor("#fc0303"))
        }
    }
}
