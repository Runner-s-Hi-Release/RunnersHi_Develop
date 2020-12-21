package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.databinding.FragmentMatchingGoalBinding
import kotlinx.android.synthetic.main.fragment_matching_goal.*

class MatchingGoalFragment : Fragment() {
    private var selectedRunTime: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMatchingGoalBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MatchingGoalFragment
                radioGroup.setOnCheckedChangeListener { _, i ->
                    btn_goal_next.isClickable = true
                    btn_goal_next.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bg_btn_goal_next)
                    btn_goal_next.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                    selectedRunTime = when (i) {
                        R.id.btn_goal_30 -> 30 * 60
                        R.id.btn_goal_45 -> 45 * 60
                        R.id.btn_goal_60 -> 60 * 60
                        R.id.btn_goal_90 -> 90 * 60
                        else -> 30 * 60
                    }
                }
                btnGoalNext.setOnClickListener{
                    findNavController().navigate(
                        MatchingGoalFragmentDirections
                            .actionMatchingGoalFragmentToMatchingGenderFragment(selectedRunTime)
                    )
                }
            }

        return binding.root
    }

}