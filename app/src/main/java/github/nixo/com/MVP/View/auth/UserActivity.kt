package github.nixo.com.MVP.View.auth

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.util.Log
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.*
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.MVP.View.fragment.FollowerFragment
import github.nixo.com.MVP.View.fragment.FollowingFragment
import github.nixo.com.MVP.View.fragment.MineRepositoryFragment
import github.nixo.com.MVP.View.fragment.StarFragment
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.utils.DownLoadUtils
import github.nixo.com.utils.dialog.LoadingDialog2
import kotlinx.android.synthetic.main.activity_edit_user.*
import org.jetbrains.anko.sdk15.listeners.onClick

class UserActivity : BaseActivity<EditUserPresent>() {

    var dialog : LoadingDialog2? = null
    var user :User? =  AccountManager.currentUser!!
    val repositoryFragment = MineRepositoryFragment()
    val followingFragment = FollowingFragment()
    val starFragment = StarFragment()
    val followerFragment = FollowerFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@UserActivity).statusBarLightFont()
        setContentView(R.layout.activity_edit_user)
        dialog = LoadingDialog2()
        initOnClick()
        initUserCard()
//        supportFragmentManager.inT { add(R.id.rv_user_fragmentContent,repositoryFragment) }
//        supportFragmentManager.inT { replace() }
    }



    fun initUserCard(){

        var stringExtra = intent.getStringExtra("newUser")
        (TextUtils.isEmpty(stringExtra)).no {
            presenter.serchOnlyUser(intent.getStringExtra("newUser"))
        }.otherwise {
            setURLGosImage(this,user!!.avatar_url,user_bg,3,2)
            user_img.loadWithGlide(user!!.avatar_url, user!!.login.first())
            user_name.text = user!!.login
            user_location.text = if(TextUtils.isEmpty(user!!.location)){"Unknow"}else{
                user!!.location}
            user_bio.text =if(TextUtils.isEmpty(user!!.bio)){"Nothing"}else{
                user!!.bio}
            user_flower.text = "${user!!.followers} Follower"
            this.user = user
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,repositoryFragment) }
        }

    }

    override fun onResume() {
        super.onResume()
    }

    fun initOnClick(){
        iv_back.onClick {
            finish()
        }
        rb_repositories.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,repositoryFragment) }
        }
        rb_followering.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,followingFragment) }
        }
        rb_start.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,starFragment) }
        }
        rb_followers.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,followerFragment) }
        }
    }




    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

}
