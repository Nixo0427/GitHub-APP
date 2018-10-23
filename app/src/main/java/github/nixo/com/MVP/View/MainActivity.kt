package github.nixo.com.MVP.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import github.nixo.com.MVP.Present.MainPresent
import github.nixo.com.github.Common.Model.AccountManager.currentUser
import github.nixo.com.github.Common.Model.User
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import kotlinx.android.synthetic.main.header_layout.*

class MainActivity : BaseActivity<MainPresent>() {
    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavUser()

    }

    fun initNavUser(){
        Log.d("Nixo----User AvatarUrl","${currentUser?.avatar_url}")
        Glide.with(this)
                .load(currentUser?.avatar_url)
                .into(nav_img)
        nav_name.text = currentUser?.login
        nav_bio.text = currentUser?.bio

    }
}
