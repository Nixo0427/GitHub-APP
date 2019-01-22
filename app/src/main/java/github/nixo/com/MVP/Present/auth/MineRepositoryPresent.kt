package github.nixo.com.MVP.Present.auth

import android.util.Log
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.MVP.View.fragment.MineRepositoryFragment
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.dialog.LoadingDialog2

class MineRepositoryPresent : BasePresenter<MineRepositoryFragment>() {
    fun  onRepository(account :String, page : Int ){
        val loadingDialog2 = LoadingDialog2().getLoadDialog(view.activity!!.supportFragmentManager)
        RepositoryService.listRepositoriesOfUser(account,page)
                .subscribe({
                    var list = it
                    view.initReposition(it)
                    logger.debug("Nixo仓库返回值"+"\n${it.hasNext}${it.hasPrev}")
                    Log.e("Nixo仓库返回值","\n${it.hasNext}${it.hasPrev}")
                    loadingDialog2.dismiss()
                },{
                    it.printStackTrace()
                    loadingDialog2.dismiss()
                })
    }

    fun onOverView(){

    }

    fun onStar(){}
    fun onFollowers(){}
    fun onFollowing(){}

}