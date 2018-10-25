package github.nixo.com.MVP.View.auth

import android.os.Bundle
import github.nixo.com.MVP.Present.RegisterPresenet
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity

class RegisterActivity :BaseActivity<RegisterPresenet>(){

    override fun onDestory() {

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

}
