package github.nixo.com.github.Common.Present

import com.ly.genjidialog.extensions.UtilsExtension.Companion.dp2px
import com.ly.genjidialog.extensions.newGenjiDialog
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.Common.View.LoginActivity
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.Common.NetWork.Services.MySubcrilber
import github.nixo.com.utils.ExcaptionUtil
import org.reactivestreams.Subscription
import rx.Observable

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

        //todo 暂时没办法调用基类MySubcrilber
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

