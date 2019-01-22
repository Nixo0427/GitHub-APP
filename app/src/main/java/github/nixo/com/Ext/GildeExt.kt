package github.nixo.com.Ext


import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import cn.carbs.android.avatarimageview.library.AppSquareAvatarImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.target.SimpleTarget
import github.nixo.com.MVP.infs.DownLoadApi
import github.nixo.com.github.NetWork.retrofit
import github.nixo.com.utils.DownLoadUtils
import github.nixo.com.utils.FastBlurUtil
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import rx.Observable


/**
 * Created by Nixo
 * @Date 2018/09/27
 * @Data Gilde图片加载框架拓展类，同时封装3个框架，并可以使用高斯模糊。（目前Uri的的高斯模糊会报错）
 */

fun AppCompatAvatarImageView.loadWithGlide(url: String, textPlaceHolder: Char, requestOptions: RequestOptions = RequestOptions()){
    (textPlaceHolder == null).yes{
        return
    }
    textPlaceHolder.toString().let {

        setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
    }
//    "Kotlin".toString().let {
//        setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
//    }

    Glide.with(this.context)
            .load(url)
            .apply(requestOptions)
            .transition( GenericTransitionOptions())
            .into(this)
}
fun AppSquareAvatarImageView.loadWithGlide(url: String, textPlaceHolder: Char, requestOptions: RequestOptions = RequestOptions()){
    textPlaceHolder.toString().let {
        setTextAndColorSeed(it.toUpperCase(), it.hashCode().toString())
    }

    Glide.with(this.context)
            .asDrawable()
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
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
    BitmapServce.getBitmapResponseBody(imgUrl).subscribe({
        Log.e("Nixo0407","${it.byteStream().toString()}")
        var bitmap = BitmapFactory.decodeStream(it.byteStream())
        //   scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
        var blurBitmap = FastBlurUtil.toBlur(bitmap, sacala,guass)
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        imageView.setImageBitmap(blurBitmap)
    },{
        Log.e("Nixo0427",it.message)
    })

}

interface getBitmapInterface{
    @GET()
    fun getBitmapResponseBody(@Url url:String ):Observable<ResponseBody>
}

object BitmapServce : getBitmapInterface by retrofit.create(getBitmapInterface::class.java)