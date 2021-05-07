package br.com.fernandodeveloper.events.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.fernandodeveloper.events.extensions.loadImage

@BindingAdapter(value = ["loadImage"])
fun ImageView.bindingLoadImage(url: String?) = loadImage(url)
