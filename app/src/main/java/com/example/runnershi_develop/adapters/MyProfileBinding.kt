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
    view.text = view.context.resources.getString(R.string.win_lose).format(win, lose)
}

@BindingAdapter("level")
fun bindLevel(view: TextView, level: Int){
    when (level) {
        1, 2, 3 -> {
            view.text = view.context.resources.getStringArray(R.array.level)[level-1]
        }
        else -> {
            view.text = ""
        }
    }
}