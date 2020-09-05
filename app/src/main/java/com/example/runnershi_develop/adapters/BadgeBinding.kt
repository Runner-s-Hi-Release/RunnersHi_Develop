package com.example.runnershi_develop.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.runnershi_develop.R

@BindingAdapter("badgeIndex")
fun bindBadgeText(view: TextView, badgeIndex: Int){
    var tvBadgeName_profile : Array<String> = arrayOf("첫승 달성", "10승 달성", "50승 달성", "최고 페이스", "최장 거리", "최저 페이스", "50시간 달성",
        "100시간 달성", "150시간 달성", "10일 연속 러닝", "연속 5승", "연속 10승")
    view.text = tvBadgeName_profile[badgeIndex]

}
@BindingAdapter("badge", "badgeIndex")
fun bindBadgeImage(view: ImageView, badge: Boolean, badgeIndex: Int){

    val colorBadgeList = listOf(
        R.drawable.img_badge_egg,
        R.drawable.img_badge_chick,
        R.drawable.img_badge_chicken,
        R.drawable.img_badge_bat,
        R.drawable.img_badge_bird,
        R.drawable.img_badge_turtle,
        R.drawable.img_badge_50,
        R.drawable.img_badge_100,
        R.drawable.img_badge_150,
        R.drawable.img_badge_straight,
        R.drawable.img_badge_speed,
        R.drawable.img_badge_flame_color)

    val blackBadgeList = listOf(
        R.drawable.img_badge_egg_empty,
        R.drawable.img_badge_chick_empty,
        R.drawable.img_badge_chicken_empty,
        R.drawable.img_badge_bat_empty,
        R.drawable.img_badge_bird_empty,
        R.drawable.img_badge_turtle_empty,
        R.drawable.img_badge_50_empty,
        R.drawable.img_badge_100_empty,
        R.drawable.img_badge_150_empty,
        R.drawable.img_badge_straight_empty,
        R.drawable.img_badge_speed_empty,
        R.drawable.img_badge_flame)

    if(badge) {
        view.setImageResource(colorBadgeList[badgeIndex])
    }
    else {
        view.setImageResource(blackBadgeList[badgeIndex])
    }
}

@BindingAdapter("badgeIndex")
fun bindBadgeDetailImage(view: ImageView, badgeIndex: Int){
    val badgeList = listOf(
        R.drawable.img_badge_egglarge,
        R.drawable.img_badge_chicklarge,
        R.drawable.img_badge_chickenlarge,
        R.drawable.img_badge_batlarge,
        R.drawable.img_badge_birdlarge,
        R.drawable.img_badge_turtlelarge,
        R.drawable.img_badge_50hourlarge,
        R.drawable.img_badge_100hourlarge,
        R.drawable.img_badge_150hourlarge,
        R.drawable.img_badge_straightlarge,
        R.drawable.img_badge_speedlarge,
        R.drawable.img_badge_flamelarge
    )
    view.setImageResource(badgeList[badgeIndex])

}

