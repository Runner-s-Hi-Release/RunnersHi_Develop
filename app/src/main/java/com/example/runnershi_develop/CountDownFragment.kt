package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.runnershi_develop.databinding.FragmentCountDownBinding
import com.example.runnershi_develop.extension.animatorListener


class CountDownFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCountDownBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CountDownFragment
            aniCoundownNumber.animatorListener {
                //TODO running화면으로 연결
            }
        }
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        return binding.root
    }
}