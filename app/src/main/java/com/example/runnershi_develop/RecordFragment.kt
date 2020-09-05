package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.runnershi_develop.databinding.FragmentRankBinding
import com.example.runnershi_develop.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentRecordBinding.inflate(inflater, container, false)

        return binding.root
    }
}