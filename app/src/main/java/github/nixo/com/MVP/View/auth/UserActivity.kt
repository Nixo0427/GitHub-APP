package github.nixo.com.MVP.View.auth

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setResGosImage
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.MVP.View.adapter.RepositoriesAdapter
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_user.*
import org.jetbrains.anko.sdk15.listeners.onClick
import retrofit2.adapter.rxjava.GitHubPaging

class UserActivity : BaseActivity<EditUserPresent>(),OnRefreshListener,OnLoadmoreListener {


    val user = AccountManager.currentUser!!
    var page = 1
    var repositoriesAdapter :RepositoriesAdapter? = null
    var manager :LinearLayoutManager? = null
    var status = 1
    var isLoadmore = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@UserActivity).statusBarLightFont()
        setContentView(R.layout.activity_edit_user)
        repositoriesAdapter = RepositoriesAdapter(this@UserActivity)
        manager = LinearLayoutManager(this@UserActivity)
        rv_baseRecylerView.layoutManager = manager
        rv_baseRecylerView.adapter = repositoriesAdapter
        srl_user_repository.setOnLoadmoreListener(this)
        srl_user_repository.setOnRefreshListener(this)
        initTest()
        initOnClick()
    }

    fun initTest(){
//        setResGosImage(this,R.drawable.user_bg,user_bg,20,30)
        setResGosImage(this,R.drawable.user_bar,user_toolbar_bg,0,80)
//        setURLGosImage(this,user.avatar_url,user_card_bg,20,50)

        user_img.loadWithGlide(user.avatar_url,user.name!!.first())
        user_location.text = user.location
        user_bio.text = user.bio
        user_flower.text = "${user.followers} Follower"
    }


    fun initOnClick(){
        rb_repositories.onClick {
            status = 2
            presenter.onRepository(user.login,page) }
    }

    fun initReposition(list :GitHubPaging<Repository>?){
        Log.e("Nixo---adapter数据","${list!!.size}")
        if(isLoadmore){
            repositoriesAdapter!!.addAll(list)
        }else{
            repositoriesAdapter!!.setDataList(list)
        }

    }


    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }
    override fun onRefresh(refreshlayout: RefreshLayout?) {
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
}
