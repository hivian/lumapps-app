package com.hivian.lumapps_test.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("firstNameText", "lastNameText")
fun TextView.setNameText(firstNameText: String, lastNameText: String) {
    text = "$firstNameText $lastNameText"
}

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
