package com.styx.mp.dddice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var sumTextView: TextView
    private lateinit var toolbar: Toolbar
    private val statistics = Statistics()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dice1 = findViewById(R.id.dice_1)
        dice2 = findViewById(R.id.dice_2)
        sumTextView = findViewById(R.id.sum_text_view)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        rollDice()

        // Set click listeners on the dice
        dice1.setOnClickListener {
            rollDice()
        }

        dice2.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceRoll1 = Random.nextInt(6) + 1
        val diceRoll2 = Random.nextInt(6) + 1
        val sum = diceRoll1 + diceRoll2

        statistics.updateStatistics(sum)

        dice1.setImageResource(getDiceImage(diceRoll1))
        dice2.setImageResource(getDiceImage(diceRoll2))
        sumTextView.text = sum.toString()
    }

    private fun getDiceImage(diceRoll: Int): Int {
        return when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.statistics -> {
                // Start StatisticsActivity and pass the counts to it
                val intent = Intent(this, StatisticsActivity::class.java)
                intent.putExtra("counts", statistics.getCounts())
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}