package github.nixo.com.github.Common.View


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ly.genjidialog.extensions.UtilsExtension.Companion.dp2px
import com.ly.genjidialog.extensions.newGenjiDialog
import com.ly.genjidialog.other.DialogGravity
import github.nixo.com.MVP.View.MainActivity
import github.nixo.com.github.Common.Present.LoginPersenter
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.utils.ExcaptionUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity :BaseActivity<LoginPersenter>(){


    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        layout_login_account.hint = resources.getString(R.string.account)
        layout_login_password.hint = resources.getString(R.string.password)

        login_login.onClick {

//            Log.e("Nixo---debug ---- account+login","${login_account.text}-----${login_password.text}")
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
//        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        action(MainActivity::class.java)
    }


    fun onLoginError(e:Throwable){
        var handleException = ExcaptionUtil.handleException(e)
        toast(handleException.message.toString())
    }

    fun onLoginStart(){

    }


}