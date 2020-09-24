package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.R
import com.example.runnershi_develop.extension.useGlide

@BindingAdapter("badgeIndex")
fun bindBadgeText(view: TextView, badgeIndex: Int){
    if(badgeIndex in 0..11)
        view.text = view.context.resources.getStringArray(R.array.badge_title)[badgeIndex]
}

@BindingAdapter("badge", "badgeIndex")
fun bindBadgeImage(view: ImageView, badge: Boolean, badgeIndex: Int){
    if(badge) {
        when(badgeIndex){
            0 -> view.useGlide(R.drawable.img_badge_egg)
            1 -> view.useGlide(R.drawable.img_badge_chick)
            2 -> view.useGlide(R.drawable.img_badge_chicken)
            3 -> view.useGlide(R.drawable.img_badge_bat)
            4 -> view.useGlide(R.drawable.img_badge_bird)
            5 -> view.useGlide(R.drawable.img_badge_turtle)
            6 -> view.useGlide(R.drawable.img_badge_50)
            7 -> view.useGlide(R.drawable.img_badge_100)
            8 -> view.useGlide(R.drawable.img_badge_150)
            9 -> view.useGlide(R.drawable.img_badge_straight)
            10 -> view.useGlide(R.drawable.img_badge_speed)
            11 -> view.useGlide(R.drawable.img_badge_flame_color)
        }
    }
    else {
        when(badgeIndex){
            0 -> view.useGlide(R.drawable.img_badge_egg_empty)
            1 -> view.useGlide(R.drawable.img_badge_chick_empty)
            2 -> view.useGlide(R.drawable.img_badge_chicken_empty)
            3 -> view.useGlide(R.drawable.img_badge_bat_empty)
            4 -> view.useGlide(R.drawable.img_badge_bird_empty)
            5 -> view.useGlide(R.drawable.img_badge_turtle_empty)
            6 -> view.useGlide(R.drawable.img_badge_50_empty)
            7 -> view.useGlide(R.drawable.img_badge_100_empty)
            8 -> view.useGlide(R.drawable.img_badge_150_empty)
            9 -> view.useGlide(R.drawable.img_badge_straight_empty)
            10 -> view.useGlide(R.drawable.img_badge_speed_empty)
            11 -> view.useGlide(R.drawable.img_badge_flame)
        }
    }
}

@BindingAdapter("badgeIndex")
fun bindBadgeDetailImage(view: ImageView, badgeIndex: Int){
    when(badgeIndex){
        0 -> view.useGlide(R.drawable.img_badge_egglarge)
        1 -> view.useGlide(R.drawable.img_badge_chicklarge)
        2 -> view.useGlide(R.drawable.img_badge_chickenlarge)
        3 -> view.useGlide(R.drawable.img_badge_batlarge)
        4 -> view.useGlide(R.drawable.img_badge_birdlarge)
        5 -> view.useGlide(R.drawable.img_badge_turtlelarge)
        6 -> view.useGlide(R.drawable.img_badge_50hourlarge)
        7 -> view.useGlide(R.drawable.img_badge_100hourlarge)
        8 -> view.useGlide(R.drawable.img_badge_150hourlarge)
        9 -> view.useGlide(R.drawable.img_badge_straightlarge)
        10 -> view.useGlide(R.drawable.img_badge_speedlarge)
        11 -> view.useGlide(R.drawable.img_badge_flamelarge)
    }
}

@BindingAdapter("badgeList")
fun bindBadgeList(view: RecyclerView, badgeList: List<Boolean?>?){
    (view.adapter as MyProfileBadgeAdapter).submitList(badgeList)
}

