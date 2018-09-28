package github.nixo.com.github.Common.View
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_PHONE_NUMBERS
import android.opengl.Visibility
import android.os.PersistableBundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.Gson
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.Common.Present.LoginPersenter
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.R
import github.nixo.com.github.mvp.IView
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.github.utils.fromJson
import github.nixo.com.github.utils.pref
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast



import org.jetbrains.anko.toast

import org.slf4j.Logger

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity :BaseActivity<LoginPersenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        addContentView(LayoutInflater.from(this).inflate(R.layout.login2,null,false))
        login_login.setOnClickListener { presenter.doLogin(login_account.text.toString(),
                login_password.text.toString()) }

    }


    fun onLoginSuccess(){
        toast("登陆成功")
    }


    fun onLoginError(e:Throwable){
        toast("登陆失败")
    }

    fun onLoginStart(){

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {

    }
}