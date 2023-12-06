package com.styx.dddice.ui.statistics

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.styx.dddice.R
import com.styx.mp.dddice.Statistics

class StatisticsAdapter(private val statistics: Statistics) : RecyclerView.Adapter<StatisticsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.statistics_item, parent, false)
        return StatisticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val counts = statistics.getCounts()
        Log.d("StatisticsAdapter", "counts: $counts")
        val totalRolls: Int = counts.values.sum()
        val value = position + 2
        val count = counts.getOrDefault(value, 0)

        val percentage = if (totalRolls != 0) (count * 100 / totalRolls) else 0

        holder.textViewValue.text = String.format("%02d", value)
        holder.textViewCount.text = String.format("%d", count)
        holder.textViewPercent.text = String.format("%02d%%", percentage)
    }

    override fun getItemCount(): Int {
        return 11
    }
}
