package github.nixo.com.MVP.Present.auth

import android.text.TextUtils
import android.util.Log
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.Ext.inT
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setURLGosImage
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.R
import github.nixo.com.github.R.mipmap.user
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.dialog.LoadingDialog2
import kotlinx.android.synthetic.main.activity_edit_user.*

class EditUserPresent : BasePresenter<UserActivity>() {

    fun serchOnlyUser(login : String){

        AuthService.serchUser(login)
                .subscribe({

                    Log.e("Nixo0407","走了走了走了走了0407")
                    if(it.items.isNotEmpty()){
                         view.user = it.items[0]
                         var user = it.items[0]
                        view.user_img.loadWithGlide(user.avatar_url,user.login.first())
                        view.user_name.text = user.login
                        view.user_location.text = if(TextUtils.isEmpty(user.location)){"Unknow"}else{user.location}
                        view.user_bio.text =if(TextUtils.isEmpty(user.bio)){"Nothing"}else{user.bio}
                        view.user_flower.text = "${user.followers} Follower"
                        setURLGosImage(view,user!!.avatar_url,view.user_bg,3,2)
//                        loadingDialog2?.dismiss()
                        view.supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,view.repositoryFragment) }

                    }
                },{
                })
    }

}