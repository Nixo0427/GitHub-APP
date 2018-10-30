package github.nixo.com.github.Common.Present

import android.support.v4.app.FragmentManager
import android.view.View
import com.ly.genjidialog.extensions.UtilsExtension.Companion.dp2px
import com.ly.genjidialog.extensions.newGenjiDialog
import com.ly.genjidialog.other.DialogGravity
import com.ly.genjidialog.other.DialogGravity.*
import github.nixo.com.Common.NetWork.Services.MySubcrilber
import github.nixo.com.MVP.View.auth.LoginActivity
import  github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.ExcaptionUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginPersenter : BasePresenter<LoginActivity>() {

    fun doLogin(account:String , pwd :String){
       var dialog =  newGenjiDialog {
            width = dp2px(view.resources,100f)
            height = dp2px(view.resources,100f)
            animStyle = R.style.ScaleADEnterExitAnimationX50Y50
            layoutId = R.layout.dialog_loding
        }.showOnWindow(view.supportFragmentManager)
      AccountManager.username = account
        AccountManager.passwd = pwd
        view.onLoginStart()

        AccountManager.login()
                .subscribe(object : MySubcrilber<Unit>() {
                    override fun onNext(t: Unit?) {
                        view.onLoginSuccess()
                        dialog.dismiss()
                    }

                    override fun onError(responseThrows: ExcaptionUtil.ResponeThrowable) {
                        dialog.dismiss()
                        view.onLoginError(responseThrows)
                    }


                })


    }

}