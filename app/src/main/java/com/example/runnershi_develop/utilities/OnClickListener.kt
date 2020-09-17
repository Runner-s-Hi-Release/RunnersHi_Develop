package com.example.runnershi_develop.utilities

class OnClickListener<T>(val clickListener: (arg: T) -> Unit) {
    fun onClick(data: T) = clickListener(data)
}