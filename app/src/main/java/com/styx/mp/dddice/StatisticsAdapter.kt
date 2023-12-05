package com.styx.mp.dddice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StatisticsAdapter(private val values: ArrayList<Int>, private val counts: ArrayList<Int>) :
    RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    private val totalRolls: Int = counts.sum()
    private val itemsCount: Int = values.size

    class StatisticsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewValue: TextView = itemView.findViewById(R.id.statistics_item_text_view_value)
        val textViewCount: TextView = itemView.findViewById(R.id.statistics_item_text_view_count)
        val textViewPercent: TextView = itemView.findViewById(R.id.statistics_item_text_view_percent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_statistics, parent, false)
        return StatisticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val count = counts[position]

        val percentage =
            if (totalRolls != 0) {
                (count * 100 / totalRolls)
            } else {
                0
            }

        holder.textViewValue.text = String.format("%02d", values[position])
        holder.textViewCount.text = String.format("%d", count)
        holder.textViewPercent.text = String.format("%02d%%", percentage)
    }

    override fun getItemCount(): Int {
        return itemsCount
    }
}