package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {

    private var player1Name: String? = null
    private var player2Name: String? = null
    private var value1: Int? = null
    private var value2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result: String? = intent.getStringExtra(PlayGame.MATCH_RESULT)

        player1Name = intent.getStringExtra(PlayGame.PLAYER1)
        player2Name = intent.getStringExtra(PlayGame.PLAYER2)
        value1 = intent.getIntExtra(PlayGame.PlAYER1_SCORE, -1)
        value2 = intent.getIntExtra(PlayGame.PlAYER2_SCORE, -1)
        if (player1Name == result) {
            value1 = value1!! + 1
        }
        if (player2Name == result) {
            value2 = value2!! + 1
        }
        player1.text = player1Name
        player2.text = player2Name
        score1.text = value1.toString()
        score2.text = value2.toString()

        if (result == "draw") {
            match_result.text = "Match Draw"
        } else {
            match_result.text = "${result} Won This Match"
        }


        new_game.setOnClickListener {
            finish()
            val intent = Intent(this, StartGame::class.java)
            startActivity((intent))
        }

        continue_game.setOnClickListener {
            finish()
            startActivity(Intent(this, PlayGame::class.java).apply {
                this.putExtra(PlayGame.PLAYER1, player2Name)
                this.putExtra(PlayGame.PlAYER1_SCORE, value2)

                this.putExtra(PlayGame.PLAYER2, player1Name)
                this.putExtra(PlayGame.PlAYER2_SCORE, value1)
            })
        }
    }
}