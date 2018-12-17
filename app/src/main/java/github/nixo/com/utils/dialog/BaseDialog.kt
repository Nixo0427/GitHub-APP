package github.nixo.com.utils.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import github.nixo.com.github.R


/**
 * @author [Nixo]
 * PS：可恶啊，为什么Kotlin封dialog这么啰嗦
 *   真香
 */
open class BaseDialog : Dialog {

    protected var mContext : Context
    var layout :  Int = 0
    var decorView : View? = null

    constructor(context: Context):super(context, R.style.NixoDialog){
        this.mContext = context

    }
    constructor(context: Context,style : Int):super(context, style){
        this.mContext = context
    }
    open val getViewId: Int
        get() = 0

//    override fun setCancelable(flag: Boolean) {
//       Logger.debug("是否设置cancelable----------> $flag")
//        super.setCancelable(flag)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId)
        initView()
        val dialogWindows= window
        val lp = dialogWindows.attributes // LayoutParams
        dialogWindows.setGravity(Gravity.CENTER)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        decorView = dialogWindows.decorView
        dialogWindows.attributes = lp
    }


    open fun initView(){}



    override fun show() {
        if(isShowing){
            return
        }
        super.show()
    }


    override fun dismiss() {
        var context = getContext()
        if(context == null || context is Activity){
            return
        }
        if(isShowing){
            super.dismiss()
        }

    }

}