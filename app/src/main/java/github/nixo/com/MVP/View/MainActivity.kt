package github.nixo.com.MVP.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.text.InputType
import android.util.Log
import cn.carbs.android.avatarimageview.library.AvatarImageView
import com.bumptech.glide.Glide
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.doOnLayoutAvailable
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.Present.MainPresent
import github.nixo.com.MVP.View.auth.LoginActivity
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.AccountManager.currentUser
import github.nixo.com.github.Common.Model.OnAccountStateChangeListener
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.drawerListener
import org.jetbrains.anko.toast

class MainActivity : BaseActivity<MainPresent>()  , OnAccountStateChangeListener {


    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@MainActivity).statusBarLightFont()
        setContentView(R.layout.activity_main)
        initToolbar()
        initNavitaionView()
    }

    private fun initEditText() {
        editText_serch.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun updataNavigationView(user: User) {
        navigation_view.doOnLayoutAvailable {
            initNavUser(user)
        }
    }

    private fun initNavitaionView() {
        AccountManager.currentUser?.let(::updataNavigationView)?:clearNavigationView()
        NavigationEvent()
        initEditText()
    }

    private fun initToolbar() {
        var actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_serch_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.drawerListener { actionBarDrawerToggle }
        actionBarDrawerToggle.syncState()
    }

    private fun NavigationEvent(){
        navigation_view.doOnLayoutAvailable {
            navigation_header.onClick {
                (AccountManager.isLoggedIn()).yes {
                    toast("用户信息页")
                }.otherwise {
                    action(LoginActivity::class.java)
                }
            }
        }
    }


    private fun initNavUser(user: User){
        Log.d("Nixo----User AvatarUrl","${currentUser?.avatar_url}")
        nav_img.loadWithGlide(currentUser!!.avatar_url,currentUser!!.login.first())
        nav_name.text = currentUser?.login?:""
        nav_bio.text = currentUser?.bio?:""

    }

    private fun clearNavigationView(){
        navigation_view.doOnLayoutAvailable {
            nav_name.text = "Please Login"
            nav_bio.text = ""
        }

    }

    override fun onLogin(user: User) {
        updataNavigationView(user)
    }

    override fun onLogout() {
        clearNavigationView()
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)
    }
}
