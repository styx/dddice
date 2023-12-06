package com.styx.dddice.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.styx.dddice.databinding.FragmentStatisticsBinding
import com.styx.dddice.ui.roll.RollViewModel
import com.styx.mp.dddice.Statistic

class StatisticsFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private lateinit var rollViewModel: RollViewModel

    private var isSortedByPercent = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rollViewModel = ViewModelProvider(requireActivity())[RollViewModel::class.java]

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val statisticsList = rollViewModel.getStatisticsList() as ArrayList<Statistic>
        statisticsList.sortBy { it.value }

        val adapter = StatisticsAdapter(statisticsList)
        binding.recyclerView.adapter = adapter

        binding.resetButton.setOnClickListener {
            resetStatistics()
        }

        binding.sortButton.setOnClickListener {
            isSortedByPercent = !isSortedByPercent
            sortStatistics()
        }

        return root
    }

    private fun resetStatistics() {
        rollViewModel.resetStatistics()
        (binding.recyclerView.adapter as StatisticsAdapter).submitList(
            rollViewModel.getStatisticsList() as ArrayList<Statistic>
        )
    }

    private fun sortStatistics() {
        val statistics = rollViewModel.getStatisticsList() as ArrayList<Statistic>
        if (isSortedByPercent) {
            statistics.sortByDescending { it.percent }
        } else {
            statistics.sortBy { it.value }
        }
        (binding.recyclerView.adapter as StatisticsAdapter).submitList(statistics)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
