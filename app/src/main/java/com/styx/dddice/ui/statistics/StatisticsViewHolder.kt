package com.styx.dddice.ui.statistics

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.styx.dddice.R

class StatisticsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textViewValue: TextView = view.findViewById(R.id.textViewValue)
    val textViewCount: TextView = view.findViewById(R.id.textViewCount)
    val textViewPercent: TextView = view.findViewById(R.id.textViewPercent)
}
