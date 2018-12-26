package github.nixo.com.MVP.View.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.MVP.Present.StarPrsent
import github.nixo.com.MVP.View.adapter.RepositoriesAdapter
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.utils.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.fragment_star.*
import retrofit2.adapter.rxjava.GitHubPaging

class StarFragment : BaseFragment<StarPrsent>(), OnRefreshListener, OnLoadmoreListener {


    var activity : UserActivity? = null
    var page = 1
    var repositoriesAdapter : RepositoriesAdapter? = null
    var manager : LinearLayoutManager? = null
    var isLoadmore = false
    var user : User? = null

    override fun setLayoutParame(): Int {
        return R.layout.fragment_star
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity  = getActivity() as UserActivity
        presenter.getStarListRepoitory(activity!!.user.login,page)
        repositoriesAdapter = RepositoriesAdapter(activity!!.baseContext,"")
        manager = LinearLayoutManager(activity)
        rv_star_repository.layoutManager = manager
        rv_star_repository.adapter = repositoriesAdapter
        srl_star_repository.setOnLoadmoreListener(this)
        srl_star_repository.setOnRefreshListener(this)

    }



    override fun onRefresh(refreshlayout: RefreshLayout?) {
        srl_star_repository!!.finishRefresh()
        page = 1
        isLoadmore = false
        presenter.getStarListRepoitory(activity!!.user.login,page)
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        srl_star_repository!!.finishLoadmore()
        page++
        isLoadmore = true
        presenter.getStarListRepoitory(activity!!.user.login,page)

    }

    fun initReposition(list : GitHubPaging<Repository>?){
        Log.e("Nixo---adapter数据","${list!!.size}")
        if(isLoadmore){
            repositoriesAdapter!!.addAll(list)
        }else{
            if(list.size == 0){
                //无内容
            }else{
                repositoriesAdapter!!.setDataList(list)
            }

        }

    }

    override fun onDestory() {
    }

}