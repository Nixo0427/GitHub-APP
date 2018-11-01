package github.nixo.com.MVP.View

import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.Ext.doOnLayoutAvailable
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.Present.MainPresent
import github.nixo.com.MVP.View.auth.EditUserActivity
import github.nixo.com.MVP.View.auth.LoginActivity
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.Common.Model.AccountManager.currentUser
import github.nixo.com.github.Common.Model.AccountManager.logout
import github.nixo.com.github.Common.Model.OnAccountStateChangeListener
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.NetWork.Services.AuthService
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.drawerListener
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : BaseActivity<MainPresent>()  , OnAccountStateChangeListener{



    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Sofia.with(this@MainActivity).statusBarLightFont()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        setNavMenuOnClickListener()
        AccountManager.onAccountStateChangeListeners.add(this)
        initNavitaionView()
    }

    private fun setNavMenuOnClickListener() {
        navigation_view.setNavigationItemSelectedListener {
            when(it!!.itemId) {
                R.id.nav_menu_logout -> {
                    Log.e("Nixo--点击事件", "退出登录")
                    alert {
                        title = "Coloud you sure logout?"
                        message = "If you logout , you just relogin form here"
                        noButton { it.dismiss() }
                        yesButton { clearNavigationView() }
                    }.show()
                    true
                }
                else->{true}
            }
        }
    }

    private fun initEditText() {
        editText_serch.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun updataNavigationView(user: User) {
        navigation_view.doOnLayoutAvailable {
            if(user == null){
                return@doOnLayoutAvailable
            }else{
                initNavUser(user)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling,menu)
        return true
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
                if(!AccountManager.isLoggedIn()) {
                    action(EditUserActivity::class.java)
                }else {
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



    override fun onLogin(user: User) {
        updataNavigationView(user)
    }

    override fun onLogout() {
        clearNavigationView()
    }
    private fun clearNavigationView(){

        navigation_view.doOnLayoutAvailable {
            nav_name.text = "Please Login"
            nav_bio.text = ""
            nav_img.setImageResource(R.mipmap.logo2)
            AccountManager.logout()
                    .subscribe({
                        toast("Logout Success")
                    },{
                        toast("${it.message}")
                    })

        }

    }
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onDestory() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)
    }


}
