package com.example.runnershi_develop.extension

import android.animation.Animator
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.runnershi_develop.R

fun LottieAnimationView.animatorListener(animationFinished: () -> Unit) {
    this.addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) = Unit

        override fun onAnimationEnd(animation: Animator?) {
            animationFinished()
        }

        override fun onAnimationCancel(animation: Animator?) = Unit

        override fun onAnimationStart(animation: Animator?) = Unit
    })
}