package github.nixo.com.MVP.View.auth.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.Common.NetWork.follow.FollowService
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.Model.Following
import github.nixo.com.MVP.Present.FollowingPresent
import github.nixo.com.MVP.View.adapter.FollowingAdapter
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.MVP.infs.adapterOnClickListener
import github.nixo.com.github.Ext.AppContext
import github.nixo.com.github.R
import github.nixo.com.utils.dialog.DialogBuilder
import github.nixo.com.utils.dialog.LoadingDialog
import github.nixo.com.utils.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.fragment_following.*
import org.jetbrains.anko.support.v4.toast

class FollowingFragment : BaseFragment<FollowingPresent>()
        , OnRefreshListener, OnLoadmoreListener , adapterOnClickListener {



    var isFirst = true
    var adapter : FollowingAdapter? = null
    var layoutManager:GridLayoutManager? = null
    var dialog:LoadingDialog? = null

    override fun setLayoutParame(): Int {
        return R.layout.fragment_following
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FollowingAdapter(AppContext,this@FollowingFragment,R.layout.item_following)
        layoutManager = GridLayoutManager(activity,2)
        rv_user_following.layoutManager = layoutManager
        rv_user_following.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        getFollowing()
    }


    private fun getFollowing(){
        FollowService.allFollowing("Nixo0427")
                .subscribe({
                    Log.e("Nixo23333",it[1].toString())
                    isFirst.yes {
                        adapter!!.setDataList(it)
                    }.otherwise {
                        adapter!!.addAll(it)
                    }
                },{
                })
    }


    override fun onRefresh(refreshlayout: RefreshLayout?) {
        refreshlayout!!.finishRefresh()
        isFirst = true
        getFollowing()
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        isFirst = false
        getFollowing()
    }
    override fun onAdapterClick(data: Any) {
        var data : Following = data as Following
        DialogBuilder().setLayout(activity!!.baseContext,R.layout.dialog_loading).show()
        toast("${data.login}")
    }


    override fun onDestory() {
    }
    override fun onViewStateResotre(saveInstanceState: Bundle?) {}


}