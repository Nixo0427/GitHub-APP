package github.nixo.com.utils.dialog

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.ly.genjidialog.extensions.UtilsExtension.Companion.dp2px
import com.ly.genjidialog.extensions.newGenjiDialog
import com.ly.genjidialog.other.DialogGravity
import com.ly.genjidialog.other.DialogInitMode
import github.nixo.com.github.R


class LoadingDialog2{
    fun getLoadDialog(fragmentManager: FragmentManager) = newGenjiDialog {
        unLeak = true
        layoutId = R.layout.dialog_loading2
        animStyle = R.style.ScaleADEnterExitAnimationX50Y50
        gravity = DialogGravity.CENTER_TOP
    }.showOnWindow(fragmentManager)

}
