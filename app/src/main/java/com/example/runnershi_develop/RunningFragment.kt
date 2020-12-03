package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.adapters.RunningAdapter
import com.example.runnershi_develop.databinding.FragmentRunningBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.utilities.OnClickListener
import com.example.runnershi_develop.viewmodels.RunningViewModel

class RunningFragment : Fragment() {
    lateinit var viewModelAdapter: RunningAdapter

    private val viewModel: RunningViewModel by viewModels {
        InjectorUtils.provideRecordViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRunningBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@RunningFragment
                viewModel = this@RunningFragment.viewModel
            }

        viewModelAdapter = RunningAdapter(OnClickListener { record ->
            viewModel.displayRecordDetail(record)
        })

        binding.recordList.adapter = viewModelAdapter


        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { record ->
            record?.let {
                this.findNavController().navigate(
                    HomeViewPagerFragmentDirections
                        .actionHomeViewPagerFragmentToRunningDetailFragment(record)
                )
                viewModel.displayRecordDetailDone()
            }
        })

        return binding.root
    }
}