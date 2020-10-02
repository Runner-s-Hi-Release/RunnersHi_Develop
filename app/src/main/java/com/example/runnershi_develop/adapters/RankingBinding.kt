package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.R
import com.example.runnershi_develop.data.RankingRunner
import com.example.runnershi_develop.extension.useGlide
import com.example.runnershi_develop.viewmodels.RankingApiStatus

@BindingAdapter("rankingList")
fun bindWinOrLoseRunnerRecyclerView(view: RecyclerView, data: List<RankingRunner>?) {
    val adapter = view.adapter as RankingRunnerAdapter
    adapter.submitList(data)
}

@BindingAdapter("scoreOrDistance")
fun bindScoreOrDistance(view: TextView, item: RankingRunner) {
    view.text = when (item) {
        is RankingRunner.WinOrLoseRunner -> view.resources.getString(R.string.win_lose)
            .format(item.win, item.lose)
        is RankingRunner.HeavyRunner -> view.resources.getString(R.string.km)
            .format(String.format("%.2f", item.distance_sum / 1000.0))
    }
}

@BindingAdapter("nickName")
fun bindNickName(view: TextView, item: RankingRunner) {
    view.text = when (item) {
        is RankingRunner.WinOrLoseRunner -> item.nickname
        is RankingRunner.HeavyRunner -> item.nickname
    }
}

@BindingAdapter("profileImage")
fun bindProfileImage(view: ImageView, item: RankingRunner) {
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
    view.useGlide(itemImage)
}