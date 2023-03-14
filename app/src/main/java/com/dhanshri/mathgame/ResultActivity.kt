package com.dhanshri.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dhanshri.mathgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val userScore = intent.getIntExtra("score",0)
        binding.txtScore.text = "Your score : $userScore"
    }
}