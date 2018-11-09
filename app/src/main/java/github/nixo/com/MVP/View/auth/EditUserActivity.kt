package github.nixo.com.MVP.View.auth

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setResGosImage
import github.nixo.com.Ext.setURLGosImage
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.MVP.View.adapter.RepositoriesAdapter
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.github.Setting.Configs
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_user.*
import org.jetbrains.anko.sdk15.listeners.onClick
import retrofit2.adapter.rxjava.GitHubPaging

class EditUserActivity : BaseActivity<EditUserPresent>() {

    val user = AccountManager.currentUser!!
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@EditUserActivity).statusBarLightFont()
        setContentView(R.layout.activity_edit_user)
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
        rb_repositories.onClick { presenter.onRepository(user.login,page) }
    }

    fun initReposition(list :GitHubPaging<Repository>?){
        Log.e("Nixo---adapter数据","${list!!.size}")
        var repositoriesAdapter = RepositoriesAdapter(this@EditUserActivity)
        var manager = LinearLayoutManager(this@EditUserActivity)
        repositoriesAdapter.setDataList(list)
        rv_baseRecylerView.layoutManager = manager
        rv_baseRecylerView.adapter = repositoriesAdapter
        repositoriesAdapter.notifyDataSetChanged()
    }


    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }
}
