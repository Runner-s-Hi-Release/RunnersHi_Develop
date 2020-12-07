package com.example.runnershi_develop.extension

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView

const val MOVE_DURATION = 300L

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


fun ConstraintLayout.move(movingDistance:Float) {
    ObjectAnimator.ofFloat(this, "translationY", movingDistance).apply {
        duration = MOVE_DURATION
        start()
    }
}