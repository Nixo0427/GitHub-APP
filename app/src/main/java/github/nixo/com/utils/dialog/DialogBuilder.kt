package github.nixo.com.utils.dialog

import android.content.Context
import android.view.View

class DialogBuilder {

    var dialog:publicDialog? =null
    var infs : DialogViewCallInterface? = null

    fun setLayout(context: Context,layout : Int): DialogBuilder{
        dialog = publicDialog(layout,context)
       return this
    }

    fun setViewCallBack(infsc : DialogViewCallInterface):DialogBuilder{
        this.infs = infsc
        return this
    }
    fun build():publicDialog{
        return dialog!!
    }

    fun show():DialogBuilder{
        dialog!!.show()
        if(infs != null){
            infs!!.ViewBack(dialog!!.decorView!!)
        }
        return this
    }


    interface DialogViewCallInterface{
       public fun ViewBack(view : View)
    }

    class  publicDialog(layoutId : Int,context: Context) : BaseDialog(context) {

        var mLayoutId = layoutId

        override val getViewId: Int
            get() = mLayoutId

    }

}