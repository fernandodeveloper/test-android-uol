package br.com.fernandodeveloper.events.extensions

import android.widget.ImageView
import br.com.fernandodeveloper.events.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.no_image_available)
            .into(this)
    } else {
        this.setImageResource(R.drawable.no_image_available)
    }
}
