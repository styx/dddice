package com.styx.mp.dddice

data class Statistic(val value: Int, val sum: Int, val percent: Int)

class Statistics {
    private val counts = mutableMapOf<Int, Int>()

    fun updateStatistics(sum: Int) {
        counts[sum] = counts.getOrDefault(sum, 0) + 1
    }

    fun getCounts(): Map<Int, Int> {
        return counts
    }

    fun toMutableList(): MutableList<Statistic> {
        val totalRolls: Int = counts.values.sum()

        return counts.map {
            Statistic(
                it.key,
                it.value,
                if (totalRolls != 0) (it.value * 100 / totalRolls) else 0
            )
        }.toMutableList()
    }

    fun reset() {
        counts.clear()
    }
}