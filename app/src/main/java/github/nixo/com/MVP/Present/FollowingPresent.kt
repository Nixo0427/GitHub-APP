package github.nixo.com.MVP.Present

import android.util.Log
import android.view.View
import github.nixo.com.Common.NetWork.follow.FollowService
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.View.fragment.FollowingFragment
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.Ext.logger
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.dialog.LoadingDialog2
import kotlinx.android.synthetic.main.public_no_data.*
import org.jetbrains.anko.support.v4.toast

class FollowingPresent : BasePresenter<FollowingFragment>() {

    fun getFollowing(){
        val loadingDialog2 = LoadingDialog2().getLoadDialog(view.activity!!.supportFragmentManager)
        FollowService.allFollowing(view.activity!!.user!!.login)
                .subscribe({
                        if (it.size == 0) {
                            view.ll_no_data.visibility = View.VISIBLE
                        } else {
                            view.ll_no_data.visibility = View.GONE
                            view.adapter!!.setDataList(it)
                            loadingDialog2.dismiss()
                        }
                },{
                    loadingDialog2.dismiss()
                    view.ll_no_data.visibility = View.VISIBLE
                    view.toast("Something Wrong!")
                })
    }

    fun unFollow(){
        FollowService.unFollowing(view.data!!.login)
                .subscribe  ({
                    view.build!!.dismiss()
                    view.toast("Unfollow Success！")
                },{})
    }


}