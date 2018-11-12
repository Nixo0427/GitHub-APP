package github.nixo.com.MVP.Present.auth

import android.util.Log
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.mvp.Impl.BasePresenter

class EditUserPresent : BasePresenter<UserActivity>() {


    fun  onRepository(account :String, page : Int ){

        RepositoryService.listRepositoriesOfUser("Nixo0427",page)
                .subscribe({
                    var list = it
                    view.initReposition(it)
                    logger.debug("Nixo仓库返回值"+"\n${it.hasNext}${it.hasPrev}")
                    Log.e("Nixo仓库返回值","\n${it.hasNext}${it.hasPrev}")
                },{
                    it.printStackTrace()
                })
    }

    fun onOverView(){

    }

    fun onStar(){}
    fun onFollowers(){}
    fun onFollowing(){}

}