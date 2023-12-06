package com.styx.dddice.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.styx.dddice.R
import com.styx.mp.dddice.Statistic

class StatisticsAdapter(private var statistics: ArrayList<Statistic>) : RecyclerView.Adapter<StatisticsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.statistics_item, parent, false)
        return StatisticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val statistic = statistics[position]
        holder.textViewValue.text = String.format("%02d", statistic.value)
        holder.textViewCount.text = String.format("%d", statistic.sum)
        holder.textViewPercent.text = String.format("%02d%%", statistic.percent)
    }

    fun submitList(newStatistics: ArrayList<Statistic>) {
        statistics = newStatistics
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return statistics.size
    }
}
