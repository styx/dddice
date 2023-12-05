package com.styx.mp.dddice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val statisticsRecyclerView: RecyclerView = findViewById(R.id.statistics_recycler_view)

        val valuesList: ArrayList<Int> = intent.getIntegerArrayListExtra("values")!!
        val countsList: ArrayList<Int> = intent.getIntegerArrayListExtra("counts")!!

        statisticsRecyclerView.adapter = StatisticsAdapter(valuesList, countsList)
    }
}