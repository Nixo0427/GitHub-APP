package github.nixo.com.Ext


import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import com.bumptech.glide.Glide
import github.nixo.com.github.R
import github.nixo.com.utils.FastBlurUtil
import jp.wasabeef.glide.transformations.BlurTransformation


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

fun setResGosImage(context: Context, imgUrl :Int, imageView : ImageView, sacala : Int,guass : Int) {
    //   获取需要被模糊的原图bitmap
    var  scaledBitmap = BitmapFactory.decodeResource(context.resources, imgUrl)
    //   scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
    var blurBitmap = FastBlurUtil.toBlur(scaledBitmap, sacala,guass)
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
    imageView.setImageBitmap(blurBitmap)
}

fun setURLGosImage(context: Context, imgUrl :String, imageView : ImageView, sacala : Int,guass : Int) {
    //   获取需要被模糊的原图bitmap
    var  scaledBitmap = FastBlurUtil.GetUrlBitmap(imgUrl,sacala,guass)
    //   scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
    var blurBitmap = FastBlurUtil.toBlur(scaledBitmap, sacala,guass)
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
    imageView.setImageBitmap(blurBitmap)
}

