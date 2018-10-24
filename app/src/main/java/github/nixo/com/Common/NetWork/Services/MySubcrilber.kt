package github.nixo.com.Common.NetWork.Services

import android.util.Log
import github.nixo.com.utils.ExcaptionUtil
import rx.Subscriber

abstract class MySubcrilber<T>: Subscriber<T>() {


    override fun onCompleted() {
        Log.d("--onCompleted--","MySubcrilber Over")
    }

    override fun onError(e: Throwable?){

        Log.d("---error---",e.toString())
        Log.d("---errorMsg---",e!!.message)

        if(e is Exception){
            onError(ExcaptionUtil.handleException(e))
        }else{
            onError( ExcaptionUtil.ResponeThrowable(e,ExcaptionUtil.ERROR.UNKNOWN))
        }

    }

    abstract fun onError(responseThrows : ExcaptionUtil.ResponeThrowable)

}