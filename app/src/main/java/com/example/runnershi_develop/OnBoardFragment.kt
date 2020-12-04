package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.runnershi_develop.adapters.VpOnBoardAdapter
import com.example.runnershi_develop.databinding.FragmentOnBoardBinding
import kotlinx.android.synthetic.main.fragment_on_board.*

class OnBoardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnBoardBinding.inflate(inflater, container, false).apply {
            vpOnBoard.adapter =
                VpOnBoardAdapter(
                    childFragmentManager,
                    4
                )
            vpOnBoard.offscreenPageLimit = 3
            vpOnBoard.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }
                override fun onPageSelected(position: Int) {
                    if(position<3){
                        btnOnBoardNext.text = getString(R.string.next)
                    }else{
                        btnOnBoardNext.text = getString(R.string.start)
                    }
                }
            })

            btnOnBoardNext.setOnClickListener {
                var position = vp_on_board.currentItem
                if(position<3){
                    vp_on_board.setCurrentItem(position+1)
                }else{
                    view?.findNavController()
                        ?.navigate(OnBoardFragmentDirections.actionOnBoardFragmentToHomeViewPagerFragment())
                }
            }
        }
        return binding.root
    }
}