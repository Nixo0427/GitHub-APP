package github.nixo.com.MVP.View.auth


import android.os.Bundle
import android.util.Log
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.MVP.View.MainActivity
import github.nixo.com.github.Common.Present.LoginPersenter
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.utils.ExcaptionUtil
import github.nixo.com.utils.dialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk15.coroutines.onClick
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity :BaseActivity<LoginPersenter>(){

    var loadingDialog : LoadingDialog? = null

    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Sofia.with(this@LoginActivity).statusBarLightFont()
        loadingDialog = LoadingDialog(this,false)
        loadingDialog!!.setCancelable(false)
        login_login.onClick {
             if(login_account.text.toString().isEmpty()){
                 Log.e("Nixo","yes")
                 layout_login_account.error = resources.getString(R.string.account_empty)
                 return@onClick
            }else{
                 layout_login_account.isErrorEnabled = false
             }
            if(login_password.text.toString().isEmpty()){
                layout_login_password.error = resources.getString(R.string.password_empty)
                return@onClick
            }else{
                layout_login_password.isErrorEnabled = false
            }
            if(login_account.text.toString().isNotEmpty() && login_password.text.toString().isNotEmpty())
            presenter.doLogin(login_account.text.toString(), login_password.text.toString())
        }
    }



    fun onLoginSuccess(){
        Log.e("Nixo","登录成功")
        toast(resources.getString(R.string.login_success))
        finish()
        action(MainActivity::class.java)
    }


    fun onLoginError(e:Throwable){
        var handleException = ExcaptionUtil.handleException(e)
        toast(handleException.message.toString())
    }

    fun onLoginStart(){

    }


}