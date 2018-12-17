package github.nixo.com.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import github.nixo.com.github.R


/**
 * @author [Nixo]
 * PS：可恶啊，为什么Kotlin封dialog这么啰嗦
 *   真香
 */
open class BaseDialog : Dialog {

    protected var mContext : Context

    constructor(context: Context):super(context, R.style.NewDialog){
        this.mContext = context
    }

    open val getViewId: Int
    get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId)
        initView()
        val dialogWindows= window
        val lp = dialogWindows.attributes // LayoutParams
        dialogWindows.setGravity(Gravity.CENTER)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
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
        if(context == null || context !is Activity){
            return
        }
        if(isShowing){
            super.dismiss()
        }

    }

}