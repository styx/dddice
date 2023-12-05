package com.styx.mp.dddice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val statisticsRecyclerView: RecyclerView = findViewById(R.id.statistics_recycler_view)

        val counts = intent.getIntArrayExtra("counts") ?: intArrayOf()
        statisticsRecyclerView.adapter = StatisticsAdapter(counts)
    }
}