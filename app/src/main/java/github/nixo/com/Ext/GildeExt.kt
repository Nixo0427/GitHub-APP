package github.nixo.com.Ext


import com.bumptech.glide.request.RequestOptions
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import com.bumptech.glide.Glide


/**
 * Created by Nixo
 */

fun AppCompatAvatarImageView.loadWithGlide(url: String, textPlaceHolder: Char, requestOptions: RequestOptions = RequestOptions()){
    textPlaceHolder.toString().let {
        setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
    }

    Glide.with(this.context)
            .load(url)
            .apply(requestOptions)
            .into(this)
}