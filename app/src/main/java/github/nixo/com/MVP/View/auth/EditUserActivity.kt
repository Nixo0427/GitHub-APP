package github.nixo.com.MVP.View.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import github.nixo.com.MVP.Present.auth.EditUserPresent
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.github.mvp.Impl.BasePresenter

class EditUserActivity : BaseActivity<EditUserPresent>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
    }


    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }
}
