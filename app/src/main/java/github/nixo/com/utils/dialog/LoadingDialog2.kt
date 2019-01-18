package github.nixo.com.utils.dialog

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import android.view.Window
import com.ly.genjidialog.extensions.newGenjiDialog
import com.ly.genjidialog.other.DialogGravity
import com.ly.genjidialog.other.DialogInitMode
import github.nixo.com.github.R

class LoadingDialog2(fragmentManager : FragmentManager )  {

//    var fragmentManager = fragmentManager
var  dialog =  newGenjiDialog {
    initMode = DialogInitMode.DRAW_COMPLETE //DialogInitMode.DRAW_COMPLETE为默认模式
        unLeak = true
    layoutId = R.layout.dialog_loading2
    dimAmount = 0f
    isFullHorizontal = true
    isFullVerticalOverStatusBar = true
    gravity = DialogGravity.CENTER_CENTER
    animStyle = R.style.AlphaEnterExitAnimation
}.showOnWindow(fragmentManager)

//
//    fun show(fragmentManager : FragmentManager ) {
//
//
//    }

    fun dismiss(){
        dialog.dismiss()
    }


}