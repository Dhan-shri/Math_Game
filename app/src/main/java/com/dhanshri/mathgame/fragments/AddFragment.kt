package com.dhanshri.mathgame.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dhanshri.mathgame.R
import com.dhanshri.mathgame.ResultActivity
import com.dhanshri.mathgame.databinding.FragmentAddBinding
import java.util.*
import kotlin.random.Random

class AddFragment : Fragment() {

    private lateinit var binding : FragmentAddBinding
    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 60000
    var timeLeftInMillis: Long = startTimerInMillis

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)

        gameContinue()

        binding.btnOk.setOnClickListener {
            val input = binding.editAns.text.toString()
            if (input == "")
            {
                Toast.makeText(context, "Please write an answer or click the next button", Toast.LENGTH_LONG).show()
            }
            else{
                pauseTimer()
                val userAnswer = input.toInt()
                if (userAnswer == correctAnswer){
                    userScore += 10
                    binding.txtQuestion.text = "Congratulation, your answer is correct"
                    binding.score.text = userScore.toString()
                }else {
                    userLife -= 1
                    binding.txtQuestion.text = "Sorry, your answer is wrong"
                    binding.life.text = userLife.toString()

                }
            }
        }

        binding.btnNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            binding.editAns.setText("")
            if (userLife == 0){
                Toast.makeText(context, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(activity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                requireActivity().finish()

            }else{
                gameContinue()
            }

        }
        return binding.root
    }

    private fun gameContinue(){
       val number1 = Random.nextInt(0,100)
       val number2 = Random.nextInt(0,100)

        binding.txtQuestion.text = "$number1 + $number2"
        correctAnswer = number1+ number2
        startTimer()
    }

    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(milliUntilFinished: Long) {
                timeLeftInMillis = milliUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife --
                binding.txtLife.text = userLife.toString()
                binding.txtQuestion.text = "Sorry, Time is up"
            }

        }.start()
    }

    fun updateText()
    {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        binding.time.text = String.format(Locale.getDefault(), "%02d",remainingTime)
    }

    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer (){
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}