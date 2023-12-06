package com.styx.dddice.ui.roll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.styx.dddice.R
import com.styx.dddice.databinding.FragmentRollBinding
import kotlin.random.Random

class RollFragment : Fragment() {

    private var _binding: FragmentRollBinding? = null
    private val binding get() = _binding!!
    private lateinit var rollViewModel: RollViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rollViewModel = ViewModelProvider(requireActivity())[RollViewModel::class.java]

        _binding = FragmentRollBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dice1 = binding.dice1.diceImage
        val dice2 = binding.dice2.diceImage

        rollDice()

        dice1.setOnClickListener {
            rollDice()
        }

        dice2.setOnClickListener {
            rollDice()
        }

        return root
    }

    private fun rollDice() {
        val diceRoll1 = Random.nextInt(6) + 1
        val diceRoll2 = Random.nextInt(6) + 1
        val sum = diceRoll1 + diceRoll2

        rollViewModel.updateStatistics(sum)

        binding.dice1.diceImage.setImageResource(getDiceImage(diceRoll1))
        binding.dice2.diceImage.setImageResource(getDiceImage(diceRoll2))
        binding.sumTextView.text = sum.toString()
    }

    private fun getDiceImage(diceRoll: Int): Int {
        return when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}