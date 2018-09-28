package github.nixo.com.github.Common.Present

import  github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.View.LoginActivity
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.Setting.Configs
import github.nixo.com.github.NetWork.entitles.AuthorizationReq
import github.nixo.com.github.mvp.Impl.BasePresenter

class LoginPersenter : BasePresenter<LoginActivity>() {

    fun doLogin(account:String , pwd :String){
      AccountManager.username = account
        AccountManager.passwd = pwd
        view.onLoginStart()
        AccountManager.login()
                .subscribe ({
                    view.onLoginSuccess()
                },{
                    view.onLoginError(it)
                })
    }




}