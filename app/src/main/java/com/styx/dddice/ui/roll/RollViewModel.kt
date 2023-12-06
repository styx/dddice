package com.styx.dddice.ui.roll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.styx.mp.dddice.Statistic
import com.styx.mp.dddice.Statistics

class RollViewModel : ViewModel() {
    private val _statistics = MutableLiveData<Statistics>()
    val statistics: LiveData<Statistics> get() = _statistics

    init {
        _statistics.value = Statistics()
    }

    fun updateStatistics(sum: Int) {
        _statistics.value?.updateStatistics(sum)
    }

    fun getStatisticsList(): MutableList<Statistic> {
        return _statistics.value?.toMutableList() ?: mutableListOf()
    }

    fun resetStatistics() {
        statistics.value?.reset()
    }
}