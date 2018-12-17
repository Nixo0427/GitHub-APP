package github.nixo.com.github.Common.Present

import github.nixo.com.Common.NetWork.Services.MySubcrilber
import github.nixo.com.MVP.View.auth.LoginActivity
import  github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.ExcaptionUtil
import github.nixo.com.utils.dialog.LoadingDialog

class LoginPersenter : BasePresenter<LoginActivity>() {

    fun doLogin(account:String , pwd :String){
     //todo 自己回家封装个dialog

        view.loadingDialog!!.show()
      AccountManager.username = "Nixo0427"
        AccountManager.passwd = "js19981226"
        view.onLoginStart()

        AccountManager.login()
                .subscribe(object : MySubcrilber<Unit>() {
                    override fun onNext(t: Unit?) {
                        view.onLoginSuccess()
                        view.loadingDialog!!.dismiss()
                    }

                    override fun onError(responseThrows: ExcaptionUtil.ResponeThrowable) {
//                        dialog.dismiss()
                        view.onLoginError(responseThrows)
                        view.loadingDialog!!.dismiss()
                    }


                })


    }

}