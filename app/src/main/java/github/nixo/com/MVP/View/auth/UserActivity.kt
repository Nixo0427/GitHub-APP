package github.nixo.com.MVP.View.auth

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.util.Log
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.inT
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setResGosImage
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.MVP.View.auth.fragment.FollowingFragment
import github.nixo.com.MVP.View.auth.fragment.MineRepositoryFragment
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_user.*
import org.jetbrains.anko.sdk15.listeners.onClick

class UserActivity : BaseActivity<EditUserPresent>() {


    public var user = AccountManager.currentUser!!
    val repositoryFragment = MineRepositoryFragment()
    val followingFragment = FollowingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@UserActivity).statusBarLightFont()
        setContentView(R.layout.activity_edit_user)
        initTest()
        initOnClick()
    }

    fun initTest(){
//        setResGosImage(this,R.drawable.user_bg,user_bg,20,30)
        setResGosImage(this,R.mipmap.default_header,user_toolbar_bg,0,80)
//        setURLGosImage(this,user.avatar_url,user_card_bg,20,50)


        if(TextUtils.isEmpty(intent.getStringExtra("newUser"))) {
            initUserCard(user)
        } else {
            presenter.serchOnlyUser(intent.getStringExtra("newUser"))
        }
    }

    fun initUserCard(user : User){
        user_img.loadWithGlide(user.avatar_url,user.login.first())
        user_name.text = user.login
        user_location.text = if(TextUtils.isEmpty(user.location)){"Unknow"}else{user.location}
        user_bio.text =if(TextUtils.isEmpty(user.bio)){"Nothing"}else{user.bio}
        user_flower.text = "${user.followers} Follower"
        this.user = user
    }


    fun initOnClick(){
        rb_repositories.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,repositoryFragment) }
        }
        rb_followering.onClick {
            supportFragmentManager.inT { replace(R.id.rv_user_fragmentContent,followingFragment) }
        }
    }




    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

}
