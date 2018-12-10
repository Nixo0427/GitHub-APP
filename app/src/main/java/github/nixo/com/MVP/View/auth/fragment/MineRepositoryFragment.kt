package github.nixo.com.MVP.View.auth.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.MVP.Present.auth.MineRepositoryPresent
import github.nixo.com.MVP.View.adapter.RepositoriesAdapter
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.activity_edit_user.*
import retrofit2.adapter.rxjava.GitHubPaging

class MineRepositoryFragment : BaseFragment<MineRepositoryPresent>(), OnRefreshListener, OnLoadmoreListener {

    var page = 1
    var repositoriesAdapter : RepositoriesAdapter? = null
    var manager : LinearLayoutManager? = null
    var status = 1
    var isLoadmore = false
    val user = AccountManager.currentUser!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoriesAdapter = RepositoriesAdapter(activity,"user")
        manager = LinearLayoutManager(activity)
        rv_baseRecylerView.layoutManager = manager
        rv_baseRecylerView.adapter = repositoriesAdapter
        srl_user_repository.setOnLoadmoreListener(this)
        srl_user_repository.setOnRefreshListener(this)



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
        refreshlayout!!.finishRefresh()

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