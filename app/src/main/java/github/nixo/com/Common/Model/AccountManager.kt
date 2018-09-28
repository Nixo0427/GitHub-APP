package github.nixo.com.github.Common.Model


import com.google.gson.Gson
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.NetWork.Services.UserService
import github.nixo.com.github.NetWork.entitles.AuthorizationReq
import github.nixo.com.github.NetWork.entitles.AuthorizationRsp
import github.nixo.com.github.utils.fromJson
import github.nixo.com.github.utils.pref
import retrofit2.HttpException
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


interface OnAccountStateChangeListener{
    fun onLogin()
    fun onLoginout()
}


object AccountManager {

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    var authId by pref(0)
    var username by pref("")
    var passwd by pref("")
    var token by pref("")

    fun isLoggedIn(): Boolean = token.isNotEmpty()


    private fun notifyLogin(user : User){
        onAccountStateChangeListeners.forEach {
            it.onLogin()
        }
    }

    private fun notifyLoginout(){
        onAccountStateChangeListeners.forEach { logout() }
    }



    private var userJson by pref("")
    private var currentUser : User? = null
    get() {
        if(field == null && userJson.isEmpty()){
            field = Gson().fromJson<User>(userJson)
        }
        return field
    }
    set(value) {
        if(value == null){
            userJson = ""
        }else{
            userJson = Gson().toJson(value)
        }
        field = value
    }

    fun login() =
        AuthService.createAuthorization(AuthorizationReq())
                .doOnNext {
                    if(it.token.isEmpty()) throw AccountException(it)

                }
                .retryWhen {
                    it.flatMap {
                        if(it is AccountException){
                            AuthService.deleteAuthorization(it.accountRsp.id)
                        }else{
                            Observable.error(it)
                        }
                    }
                }
                .flatMap {
                    token = it.token
                    authId = it.id
                    UserService.getAuthenticatedUser()
                }
                .map {
                    currentUser = it
                    notifyLogin(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())


    fun logout() = AuthService.deleteAuthorization(authId)
            .doOnNext{
                if(it.isSuccessful){
                    authId = -1
                    currentUser = null
                    token = ""
                    notifyLoginout()
                }else{
                    throw HttpException(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    class AccountException(val accountRsp : AuthorizationRsp):Exception("Already Login")
}
