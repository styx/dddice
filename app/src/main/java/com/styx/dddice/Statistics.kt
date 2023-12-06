package com.styx.mp.dddice

class Statistics {
    private val counts = mutableMapOf<Int, Int>()

    fun updateStatistics(sum: Int) {
        counts[sum] = counts.getOrDefault(sum, 0) + 1
    }

    fun getCounts(): Map<Int, Int> {
        return counts
    }

    fun reset() {
        counts.clear()
    }
}