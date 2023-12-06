package com.styx.dddice.ui.statistics

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.styx.dddice.databinding.FragmentStatisticsBinding
import com.styx.dddice.ui.roll.RollViewModel

class StatisticsFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private lateinit var rollViewModel: RollViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rollViewModel = ViewModelProvider(requireActivity())[RollViewModel::class.java]

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = StatisticsAdapter(rollViewModel.getStatistics())
        binding.recyclerView.adapter = adapter

        Log.d("StatisticsFragment", "statistics changed")
        rollViewModel.statistics.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
