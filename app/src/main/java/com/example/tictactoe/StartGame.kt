package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class StartGame : AppCompatActivity() {


    companion object {
        val PLAYER1 = "player1"
        val PLAYER2 = "player2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player1.requestFocus();
        start.setOnClickListener {
            if (player1.text.isEmpty() || player2.text.isEmpty()) {
                Toast.makeText(this, "Please provide the name of both players", Toast.LENGTH_SHORT)
                    .show()
            } else {
                intent = Intent(this, PlayGame::class.java)
                intent.putExtra(PLAYER1, player1.text.toString())
                intent.putExtra(PLAYER2, player2.text.toString())
                finish()
                startActivity(intent)

            }
        }


    }


}