package github.nixo.com.github.Common.Present

import android.view.View
import  github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.View.LoginActivity
import github.nixo.com.github.mvp.Impl.BasePresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginPersenter : BasePresenter<LoginActivity>() {

    fun doLogin(account:String , pwd :String){
        view.lodding.visibility = View.VISIBLE
      AccountManager.username = account
        AccountManager.passwd = pwd
        view.onLoginStart()

        AccountManager.login()
                .subscribe ({
                    view.lodding.visibility = View.GONE
                    view.onLoginSuccess()
                },{
                    view.lodding.visibility = View.GONE
                    view.onLoginError(it)
                })
    }




}