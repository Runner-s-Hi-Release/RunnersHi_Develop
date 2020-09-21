package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.adapters.RecordAdapter
import com.example.runnershi_develop.databinding.FragmentRecordBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.RecordViewModel

class RecordFragment : Fragment() {
    lateinit var viewModelAdapter: RecordAdapter

    private val viewModel: RecordViewModel by viewModels {
        InjectorUtils.provideRecordViewModelFactory(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentRecordBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@RecordFragment
            }

        viewModelAdapter = RecordAdapter(RecordAdapter.OnClickListener{record ->
            viewModel.displayRecordDetail(record)
        })

        binding.recordList.adapter =viewModelAdapter

        viewModel.runnings.observe(viewLifecycleOwner, Observer {
            viewModelAdapter.submitList(it)
        })

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {record ->
            this.findNavController().navigate(
                HomeViewPagerFragmentDirections
                    .actionHomeViewPagerFragmentToRecordDetailFragment(record)
            )
            viewModel.displayRecordDetailDone()
        })

        return binding.root
    }
}