package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.R
import com.example.runnershi_develop.data.OpponentRunningDetail
import com.example.runnershi_develop.data.Running
import com.example.runnershi_develop.extension.useGlide

@BindingAdapter("winOrLoseBackgroundImage")
fun bindWinOrLoseBackground(imageView: ImageView, result: Int) {
    if (result == 1) {
        imageView.useGlide(R.drawable.blueline___recbadgefragment_winnerrecord)
    } else {
        imageView.useGlide(R.drawable.grayline___recbadgefragment_winnerrecord)
    }
}

@BindingAdapter("runningResultLayout")
fun bindRunningResultLayout(layout: ConstraintLayout, result: Int) {
    if (result == 1) {
        layout.setBackgroundResource(R.drawable.bluebox_recdetailactivity)
    } else {
        layout.setBackgroundResource(R.drawable.graybox_recdetailactivity)
    }
}

@BindingAdapter("date")
fun bindDate(textView: TextView, date: String) {
    val sliced = date.split("-")
    val newFormatDate = "${sliced[0]}.${sliced[1]}.${sliced[2]}"
    textView.text = newFormatDate
}

@BindingAdapter("distance")
fun bindDistance(textView: TextView, meter: Int?) {
    meter?.let {
        val km = meter / 1000.0
        textView.text = String.format("%.2f", km)
    }
}

@BindingAdapter("time")
fun bindTime(textView: TextView, time: String?) {
    time?.let {
        with(it.split(":")) {
            /*textView.text = if (this[0] == "00") {
                "${this[1]}:${this[2]}"
            } else {
                "${this[0][1]}:${this[1]}:${this[2]}"
            }*/
        }
    }
}

@BindingAdapter("listData")
fun bindListData(recyclerView: RecyclerView, runnings: List<Running>?) {
    runnings?.let {
        val adapter = recyclerView.adapter as RunningAdapter
        adapter.submitList(runnings)
    }
}

@BindingAdapter("opponentIsVisible")
fun bindVisible(layout: ConstraintLayout, opponentRunningDetail: OpponentRunningDetail?) {
    layout.isVisible = opponentRunningDetail != null
}