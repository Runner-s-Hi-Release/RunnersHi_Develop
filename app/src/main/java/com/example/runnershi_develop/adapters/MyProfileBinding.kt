package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.runnershi_develop.R

@BindingAdapter("profileImage")
fun bindProfileImage(view: ImageView, profile: Int){
    val imgvProfile : Array<Int> = arrayOf(
        R.drawable.icon_redman_shorthair,
        R.drawable.icon_blueman_shorthair,
        R.drawable.icon_redman_basichair,
        R.drawable.icon_blueman_permhair,
        R.drawable.icon_redwomen_ponytail,
        R.drawable.icon_bluewomen_ponytail,
        R.drawable.icon_redwomen_shortmhair,
        R.drawable.icon_bluewomen_permhair,
        R.drawable.icon_redwomen_bunhair)
    if(profile > 0)
        view.setImageResource(imgvProfile[profile-1])
}

@BindingAdapter("win", "lose")
fun bindSetScore(view: TextView, win: Int, lose: Int){
    view.text = "${win}승 ${lose}패"
}

@BindingAdapter("level")
fun bindLevel(view: TextView, level: Int){
    when (level) {
        1 -> {
            view.text = "초급"
        }
        2 -> {
            view.text = "중급"
        }
        3 -> {
            view.text = "고급"
        }
        else -> {
            view.text = ""
        }
    }
}

