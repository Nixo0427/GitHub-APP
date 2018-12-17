package github.nixo.com.utils.dialog

import android.content.Context
import android.os.Bundle
import github.nixo.com.github.R

class LoadingDialog (context :Context,isCancel:Boolean):BaseDialog(context,R.style.NixoDialog) {

    var isCancel = isCancel

    override val getViewId: Int
        get() = R.layout.dialog_loading

//    override fun getContentResourceId(): Int {
//        return super.getContentResourceId()
//        return R.layout.dialog_loading
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setCancelable(isCancel)
    }
}