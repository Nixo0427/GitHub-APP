package github.nixo.com.github.Common.Present

import com.ly.genjidialog.extensions.UtilsExtension.Companion.dp2px
import com.ly.genjidialog.extensions.newGenjiDialog
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.View.auth.LoginActivity
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter

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
                .subscribe ({
                    dialog.dismiss()
                    view.onLoginSuccess()
                },{
                    dialog.dismiss()
                    view.onLoginError(it)
                })
    }




}