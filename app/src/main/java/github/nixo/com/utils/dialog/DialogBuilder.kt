package github.nixo.com.utils.dialog

import android.content.Context
import android.os.Bundle
import android.view.View

class DialogBuilder {

    var dialog:publicDialog? =null
    var infs : DialogViewCallInterface? = null

    fun setLayout(context: Context,layout : Int): DialogBuilder{
        dialog = publicDialog(layout,context)
       return this
    }

    fun setViewCallBack(infs : DialogViewCallInterface):DialogBuilder{
        this.infs == infs
        return this
    }
    fun build():publicDialog{
        return dialog!!
    }

    fun show():DialogBuilder{
        if(infs != null){
            infs!!.ViewBack(dialog!!.decorView!!)
        }

        dialog!!.show()
        return this
    }


    interface DialogViewCallInterface{
        fun ViewBack(view : View)
    }

    class  publicDialog(layout : Int,context: Context) : BaseDialog(context){




        override val getViewId: Int
            get() = layout


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(layout)
//            decorView
        }
    }

}