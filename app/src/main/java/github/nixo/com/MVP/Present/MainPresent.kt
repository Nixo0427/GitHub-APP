package github.nixo.com.MVP.Present

import android.util.Log
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.Common.NetWork.Services.EventServce
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.View.MainActivity
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.github.mvp.Impl.BasePresenter
import java.util.*

class MainPresent : BasePresenter<MainActivity>() {

    fun getPublicResitorestry(page :Int){
        RepositoryService.allRepositories(page,"pushed:<"+android.text.format.DateFormat.format("yyyy-MM-dd", Date()))
                .subscribe({
                    Log.e("仓库返回值","\n${it.paging.hasNext}${it.paging.hasPrev}")
//                    view.initAllRepository(it.items!!)
                },{
                    it.printStackTrace()
                })
    }



}