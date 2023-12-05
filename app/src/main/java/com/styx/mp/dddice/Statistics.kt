package com.styx.mp.dddice

class Statistics {
    private val counts = IntArray(13) { 0 } // Indices 0 and 1 are not used

    fun updateStatistics(sum: Int) {
        counts[sum]++
    }

    fun getCounts(): IntArray {
        return counts
    }

    fun reset() {
        for (i in counts.indices) {
            counts[i] = 0
        }
    }
}