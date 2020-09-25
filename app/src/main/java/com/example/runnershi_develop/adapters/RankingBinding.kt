package com.example.runnershi_develop.adapters

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.R
import com.example.runnershi_develop.data.RankingRunner
import com.example.runnershi_develop.extension.useGlide
import com.example.runnershi_develop.viewmodels.RankingApiStatus

@BindingAdapter("listData")
fun RecyclerView.bindWinOrLoseRunnerRecyclerView(data: List<RankingRunner.WinOrLoseRunner>?) {
    val adapter = adapter as RankingRunnerAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun RecyclerView.bindHeavyRunnerRecyclerView(data: List<RankingRunner.HeavyRunner>?) {
    val adapter = adapter as RankingRunnerAdapter
    adapter.submitList(data)
}

@BindingAdapter("scoreOrDistance")
fun TextView.bindScoreOrDistance(item: RankingRunner) {
    text = when (item) {
        is RankingRunner.WinOrLoseRunner -> resources.getString(R.string.win_lose)
            .format(item.win, item.lose)
        is RankingRunner.HeavyRunner -> resources.getString(R.string.km)
            .format(String.format("%.2f", item.distance_sum / 1000.0))
    }
}

@BindingAdapter("nickName")
fun TextView.bindNickName(item: RankingRunner) {
    text = when (item) {
        is RankingRunner.WinOrLoseRunner -> item.nickname
        is RankingRunner.HeavyRunner -> item.nickname
    }
}

@BindingAdapter("profileImage")
fun ImageView.bindProfileImage(item: RankingRunner) {
    val itemImageIndex = when (item) {
        is RankingRunner.WinOrLoseRunner -> item.image
        is RankingRunner.HeavyRunner -> item.image
    }
    val itemImage = when (itemImageIndex) {
        1 -> R.drawable.icon_redman_shorthair
        2 -> R.drawable.icon_blueman_shorthair
        3 -> R.drawable.icon_redman_basichair
        4 -> R.drawable.icon_blueman_permhair
        5 -> R.drawable.icon_redwomen_ponytail
        6 -> R.drawable.icon_bluewomen_ponytail
        7 -> R.drawable.icon_redman_shorthair
        8 -> R.drawable.icon_redwomen_shortmhair
        else -> R.drawable.icon_redwomen_bunhair
    }
    useGlide(itemImage)
}