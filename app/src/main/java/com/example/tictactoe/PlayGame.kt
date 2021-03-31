package com.example.tictactoe

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_play_game.*

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


class PlayGame : AppCompatActivity(), View.OnClickListener {

    private var possibleAnswers: List<List<TextView>>? = null

    companion object {
        val PLAYER1 = "player1"
        val PLAYER2 = "player2"
        val PlAYER1_SCORE = "score1"
        val PlAYER2_SCORE = "score2"
        val MATCH_RESULT = "matchResult"
    }

    private var count: Int = 0
    private var scoreValue1 = 0
    private var scoreValue2 = 0
    private var player1: String? = null
    private var player2: String? = null
    private var player1Score: TextView? = null
    private var player2Score: TextView? = null
    private var currentPlayerSign = "X"
    private var currentPlayer: String? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)
        possibleAnswers =
            listOf(listOf(OX1, OX2, OX3),
                listOf(OX4, OX5, OX6),
                listOf(OX7, OX8, OX9),
                listOf(OX1, OX4, OX7),
                listOf(OX2, OX5, OX8),
                listOf(OX3, OX6, OX9),
                listOf(OX1, OX5, OX9),
                listOf(OX3, OX5, OX7))
        player1 = intent.getStringExtra(StartGame.PLAYER1)
        player2 = intent.getStringExtra(StartGame.PLAYER2)
        scoreValue1 = intent.getIntExtra(PlAYER1_SCORE, 0)
        scoreValue2 = intent.getIntExtra(PlAYER2_SCORE, 0)
        addViews()
        currentPlayer = player2
        turn.text = "$player1's Turn"


        OX1.setOnClickListener(this)
        OX2.setOnClickListener(this)
        OX3.setOnClickListener(this)
        OX4.setOnClickListener(this)
        OX5.setOnClickListener(this)
        OX6.setOnClickListener(this)
        OX7.setOnClickListener(this)
        OX8.setOnClickListener(this)
        OX9.setOnClickListener(this)


    }


    override fun onClick(v: View) {

        when (v.id) {
            R.id.OX1 -> {
                OX1.text = getCurrentSign()
            }
            R.id.OX2 -> {
                OX2.text = getCurrentSign()
            }
            R.id.OX3 -> {
                OX3.text = getCurrentSign()
            }
            R.id.OX4 -> {
                OX4.text = getCurrentSign()
            }
            R.id.OX5 -> {
                OX5.text = getCurrentSign()
            }
            R.id.OX6 -> {
                OX6.text = getCurrentSign()
            }
            R.id.OX7 -> {
                OX7.text = getCurrentSign()
            }
            R.id.OX8 -> {
                OX8.text = getCurrentSign()
            }
            R.id.OX9 -> {
                OX9.text = getCurrentSign()
            }
        }
        turn.text = "${getCurrentPlayer()}'s Turn"
        count++
        checkAnswer()
    }

    private fun sendIntent(s: String) {
        val resultIntent = Intent(this, Result::class.java)
        resultIntent.putExtra(PLAYER1, player1)
        resultIntent.putExtra(PLAYER2, player2)
        resultIntent.putExtra(PlAYER1_SCORE, scoreValue1)
        resultIntent.putExtra(PlAYER2_SCORE, scoreValue2)
        resultIntent.putExtra(MATCH_RESULT, s)
        finish()
        startActivity(resultIntent)
    }

    fun checkAnswer() {
        for (i in possibleAnswers!!) {
            val O = "O"
            val X = "X"
            if (O == i[0].text.toString() && O == i[1].text.toString() && O == i[2].text.toString()) {
                sendIntent(player1!!)
            }

            if (X == i[0].text.toString() && X == i[1].text.toString() && X == i[2].text.toString()) {
                sendIntent(player2!!)
            }
        }
        if (count == 9) {
            sendIntent("draw")
        }
    }

    private fun getCurrentSign(): String {
        if (this.currentPlayerSign == "O") {
            currentPlayerSign = "X"
            return "X"
        } else {
            currentPlayerSign = "O"
            return "O"
        }
    }

    private fun getCurrentPlayer(): String {
        val player: String = currentPlayer!!
        currentPlayer = if (currentPlayer == player1) player2 else player1
        return player
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun addViews() {

        //Dynamic TextView for first Player Name
        val player1View: TextView = TextView(this).apply {
            this.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                leftToLeft = contains_score.id
                topToTop = contains_score.id
                leftMargin = 20.px
                topMargin = 20.px
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
            id = ViewGroup.generateViewId()
            text = player1
        }


        //Dynamic TextView for second Player Name
        val player2View: TextView = TextView(this).apply {
            this.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToTop = contains_score.id
                rightToRight = contains_score.id
                rightMargin = 20.px
                topMargin = 20.px
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
            text = player2
            id = ViewGroup.generateViewId()
        }

        //Show the current Score of Player1
        this.player1Score = TextView(this).apply {

            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = player1View.id
                leftToLeft = player1View.id
                rightToRight = player1View.id
                gravity = Gravity.CENTER
                leftMargin = 20.px
                topMargin = 10.px
            }
            gravity = Gravity.CENTER
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            text = scoreValue1.toString()
            id = ViewGroup.generateViewId()
        }

        //Show the current Score of Player2
        this.player2Score = TextView(this).apply {

            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = player2View.id
                rightToRight = player2View.id
                leftToLeft = player2View.id
                gravity = Gravity.CENTER
                rightMargin = 20.px
                topMargin = 10.px
            }
            gravity = Gravity.CENTER
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            text = scoreValue2.toString()
            id = ViewGroup.generateViewId()
        }
        contains_score.addView(player1View)
        contains_score.addView(player2View)
        contains_score.addView(player1Score)
        contains_score.addView(player2Score)
    }


}
