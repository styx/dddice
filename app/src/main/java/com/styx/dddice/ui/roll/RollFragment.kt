package com.styx.dddice.ui.roll

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

        if (sum == 7) {
            binding.sumTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        } else {
            binding.sumTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.bigText))
        }

        vibrate()
    }

    private fun vibrate() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context?.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        vibrator.vibrate(VibrationEffect.createOneShot(50, 1))
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