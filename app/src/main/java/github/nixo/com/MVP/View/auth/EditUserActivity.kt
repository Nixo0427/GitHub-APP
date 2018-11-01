package github.nixo.com.MVP.View.auth

import android.os.Bundle
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.setResGosImage
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.github.Setting.Configs
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_user.*

class EditUserActivity : BaseActivity<EditUserPresent>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@EditUserActivity).statusBarLightFont()
        setContentView(R.layout.activity_edit_user)
        initTest()
    }

    fun initTest(){
        setResGosImage(this,R.drawable.user_bg,user_bg,20,30)
        setResGosImage(this,R.drawable.user_bar,user_toolbar_bg,0,80)
        var user = AccountManager.currentUser!!
        user_img.loadWithGlide(user.avatar_url,user.name!!.first())
        user_location.text = user.location
        user_bio.text = user.bio
        user_flower.text = "${user.followers} Follower"
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }
}
