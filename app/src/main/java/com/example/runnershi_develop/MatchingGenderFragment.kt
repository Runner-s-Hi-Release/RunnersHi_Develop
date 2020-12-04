package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.runnershi_develop.data.RunningStart
import com.example.runnershi_develop.databinding.FragmentMatchingGenderBinding
import kotlinx.android.synthetic.main.fragment_matching_gender.*

class MatchingGenderFragment : Fragment() {
    private var selectedGender: Int = 0
    private val args: MatchingGenderFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMatchingGenderBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MatchingGenderFragment
                radioGroup.setOnCheckedChangeListener { _, i ->
                    btn_rival_next.isClickable = true
                    btn_rival_next.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bg_btn_goal_next)
                    btn_rival_next.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                    selectedGender = when (i) {
                        R.id.btn_goal_30 -> 30 * 60
                        R.id.btn_goal_45 -> 45 * 60
                        R.id.btn_goal_60 -> 30 * 60
                        R.id.btn_goal_90 -> 45 * 60
                        else -> 30 * 60
                    }
                }
                btnRivalNext.setOnClickListener{
                    findNavController().navigate(
                        MatchingGenderFragmentDirections.actionMatchingGenderFragmentToMatchingFragment(
                            RunningStart(args.goal, selectedGender)
                        )
                    )
                }
            }

        return binding.root
    }

}