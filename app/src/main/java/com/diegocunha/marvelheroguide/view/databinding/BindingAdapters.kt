package com.diegocunha.marvelheroguide.view.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.diegocunha.marvelheroguide.model.data.Image

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide
                .with(view.context)
                .load(it)
                .into(view)
    }
}

@BindingAdapter("imageUri")
fun loadImageUri(view: ImageView, url: Image?) {
    url?.let {
        Glide
                .with(view.context)
                .load(it.path + "." + it.extension)
                .into(view)
    }
}


@BindingAdapter("visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}