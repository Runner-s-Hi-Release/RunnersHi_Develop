package com.example.runnershi_develop.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideExtension

fun ImageView.useGlide(imageResource: Int?) {
    Glide.with(this.context)
        .load(imageResource)
        .into(this)
}