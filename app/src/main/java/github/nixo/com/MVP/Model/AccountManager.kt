package github.nixo.com.github.Common.Model


import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import github.nixo.com.Ext.Preference
import github.nixo.com.github.Ext.AppContext
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.NetWork.Services.UserService
import github.nixo.com.github.NetWork.entitles.AuthorizationReq
import github.nixo.com.github.NetWork.entitles.AuthorizationRsp
import github.nixo.com.github.utils.fromJson
import github.nixo.com.github.utils.pref
import retrofit2.HttpException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.prefs.Preferences
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


interface OnAccountStateChangeListener{
    fun onLogin(user: User)
    fun onLogout()
}

object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var passwd by pref("")
    var token by pref("")

    private var userJson by pref("")

    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if(value == null){
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLogout() {
        onAccountStateChangeListeners.forEach {
            it.onLogout()
        }
    }

    fun isLoggedIn(): Boolean = token.isEmpty()

    fun login()  =
            AuthService.createAuthorization(AuthorizationReq())

                    .doOnNext {
                        Log.d("Nixo--GetUserInfo--->",it.toString())

                        if (it.token.isEmpty()) {
                            authId = it.id
                            throw AccountException(it)
                        }
                    }
                    .retryWhen {
                        it.flatMap {
                            if (it is AccountException) {
                                logout()
                            } else {
                                Observable.error(it)
                            }
                        }
                    }
                    .flatMap {
                        Log.d("Nixo-----获取Token返回值 token+authId","Token:-->"+it.token+"---"+"authId"+"---"+it.id)
                        token = it.token
                        authId = it.id
                        UserService.getAuthenticatedUser()

                    }
                    .map {
                        currentUser = it
                        notifyLogin(it)
                    }



    fun logout() = AuthService.deleteAuthorization(authId)

            .doOnNext {
                Log.d("Nixo--deleteAuthorization--->",it.toString())
                if (it.isSuccessful) {
                    authId = -1
                    token = ""
                    currentUser = null
                    notifyLogout()
//                    Preference<Any>(AppContext,"pref",token,"").deleteSpref()
//                    notifyLogout()
                } else {
                    throw HttpException(it)
                }
            }



    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}
