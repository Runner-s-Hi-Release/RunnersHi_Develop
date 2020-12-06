package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.runnershi_develop.R
import com.example.runnershi_develop.extension.useGlide

@BindingAdapter("profileImage")
fun bindProfileImage(view: ImageView, profile: Int) {
    when (profile) {
        1 -> view.useGlide(R.drawable.icon_redman_shorthair)
        2 -> view.useGlide(R.drawable.icon_blueman_shorthair)
        3 -> view.useGlide(R.drawable.icon_redman_basichair)
        4 -> view.useGlide(R.drawable.icon_blueman_permhair)
        5 -> view.useGlide(R.drawable.icon_redwomen_ponytail)
        6 -> view.useGlide(R.drawable.icon_bluewomen_ponytail)
        7 -> view.useGlide(R.drawable.icon_redwomen_shortmhair)
        8 -> view.useGlide(R.drawable.icon_bluewomen_permhair)
        9 -> view.useGlide(R.drawable.icon_redwomen_bunhair)
    }
}

@BindingAdapter("win", "lose")
fun bindSetScore(view: TextView, win: Int, lose: Int) {
    view.text = view.context.resources.getString(R.string.win_lose).format(win, lose)
}

@BindingAdapter("level")
fun bindLevel(view: TextView, level: Int) {
    when (level) {
        1, 2, 3 -> {
            view.text = view.context.resources.getStringArray(R.array.level)[level - 1]
        }
        else -> {
            view.text = ""
        }
    }
}