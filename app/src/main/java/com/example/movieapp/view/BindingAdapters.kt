package com.example.movieapp.view

import android.content.res.Resources
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl != null) {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .into(imgView)
    } else {
        val drawable = Resources.getSystem().getDrawable(android.R.drawable.ic_menu_close_clear_cancel)
        imgView.setImageDrawable(drawable)
    }
}
