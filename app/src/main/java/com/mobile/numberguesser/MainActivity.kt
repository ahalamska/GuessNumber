package com.mobile.numberguesser

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var num : Int = 0
    private var counter : Int = 0
    private var bestScore : Int = -1
    private var gameRunning : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNewGame()
        newGame.setOnClickListener{
            createNewGame()
        }
        sendGuess.setOnClickListener{
            if(!gameRunning){
                createNewGame()
            }
            val guess = try {
                guessField.text.toString().toInt()
            } catch(e : Exception){
                -1
            }
            handleOutput(guess)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun createNewGame(){
        resultField.text = "New Game"
        resultField.setTextColor(Color.BLACK)
        counter = 0
        this.num = Random.nextInt(0,20)
        counterField.text = counter.toString()
        gameRunning = true
    }

    private fun winGame(){
        if(bestScore == -1){
            bestScore = counter
        }
        else if(counter < bestScore){
            bestScore = counter
        }
        statisticsOutput.text = bestScore.toString()
        gameRunning = false
    }

    @SuppressLint("SetTextI18n")
    private fun handleOutput(guess: Int) {
        counter++
        when{
            guess > 20 || guess < 0 ->{
                resultField.text = "The input you gave is out of scope"
                resultField.setTextColor(Color.RED)
            }
            guess == num -> {
                resultField.text = "Good job! The number is $guess"
                resultField.setTextColor(Color.GREEN)
                winGame()
            }
            guess > num -> {
                resultField.text = "The number is smaller than $guess"
                resultField.setTextColor(Color.BLUE)
            }
            else -> {
                resultField.text = "The number is bigger than $guess"
                resultField.setTextColor(Color.BLUE)
            }
        }
        counterField.text = counter.toString()

    }


}
