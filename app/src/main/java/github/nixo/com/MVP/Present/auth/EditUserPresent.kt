package github.nixo.com.MVP.Present.auth

import android.util.Log
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.R.mipmap.user
import github.nixo.com.github.mvp.Impl.BasePresenter

class EditUserPresent : BasePresenter<UserActivity>() {

    fun serchOnlyUser(login : String){
        AuthService.serchUser(login)
                .subscribe({
                    Log.e("Nixo0407","走了走了走了走了0407")
                    if(it.items.isNotEmpty()){
                        var user = it.items[0]
                        view.initUserCard(user)
                    }
                },{})
    }

}