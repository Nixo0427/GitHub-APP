package github.nixo.com.MVP.View.auth.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.MVP.Present.auth.MineRepositoryPresent
import github.nixo.com.MVP.View.adapter.RepositoriesAdapter
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.R
import github.nixo.com.utils.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine_repository.*
import retrofit2.adapter.rxjava.GitHubPaging

class MineRepositoryFragment : BaseFragment<MineRepositoryPresent>(), OnRefreshListener, OnLoadmoreListener {
    override fun setLayoutParame(): Int {
        return R.layout.fragment_mine_repository
    }


    var page = 1
    var repositoriesAdapter : RepositoriesAdapter? = null
    var manager : LinearLayoutManager? = null
    var status = 2
    var isLoadmore = false
    val user = AccountManager.currentUser!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        repositoriesAdapter = RepositoriesAdapter(activity!!.baseContext,"user")
        manager = LinearLayoutManager(activity)
        rv_user_repository.layoutManager = manager
        rv_user_repository.adapter = repositoriesAdapter
        srl_user_repository.setOnLoadmoreListener(this)
        srl_user_repository.setOnRefreshListener(this)
        presenter.onRepository(user.login,page)
    }

    fun initReposition(list : GitHubPaging<Repository>?){
        Log.e("Nixo---adapter数据","${list!!.size}")
        if(isLoadmore){
            repositoriesAdapter!!.addAll(list)
        }else{
            repositoriesAdapter!!.setDataList(list)
        }

    }
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        refreshlayout!!.finishRefresh()
        page = 1
        isLoadmore = false
        when(status){
            1->{}
            2->{ presenter.onRepository(user.login,page)}
        }

    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        refreshlayout!!.finishLoadmore()
        page++

        isLoadmore = true
        when(status){
            1->{}
            2->{ presenter.onRepository(user.login,page)}
        }

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
}