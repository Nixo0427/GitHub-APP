package github.nixo.com.MVP.Present.auth

import android.view.View
import github.nixo.com.Common.NetWork.follow.FollowService
import github.nixo.com.MVP.View.fragment.FollowerFragment
import github.nixo.com.github.mvp.Impl.BasePresenter
import kotlinx.android.synthetic.main.public_no_data.*
import org.jetbrains.anko.support.v4.toast

class FollowerPresent : BasePresenter<FollowerFragment>() {
    fun getFollowing(){
        FollowService.allFollower(view.activity!!.user.login)
                .subscribe({
                    if (it.size == 0) {
                        view.ll_no_data.visibility = View.VISIBLE
                    } else {
                        view.ll_no_data.visibility = View.GONE
                        view.adapter!!.setDataList(it)
                    }
                },{
                    view.ll_no_data.visibility = View.VISIBLE
                    view.toast("Something Wrong!")
                })
    }

}