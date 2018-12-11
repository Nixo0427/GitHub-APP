package github.nixo.com.MVP.View.auth

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.inT
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setResGosImage
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.MVP.View.auth.fragment.MineRepositoryFragment
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_user.*
import org.jetbrains.anko.sdk15.listeners.onClick

class UserActivity : BaseActivity<EditUserPresent>() {


    val user = AccountManager.currentUser!!


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

        user_img.loadWithGlide(user.avatar_url,user.name!!.first())
        user_location.text = user.location
        user_bio.text = user.bio
        user_flower.text = "${user.followers} Follower"
    }




    fun initOnClick(){
        rb_repositories.onClick {
           supportFragmentManager.inT { add(R.id.rv_user_fragmentContent,MineRepositoryFragment()) }
        }
    }




    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

}
