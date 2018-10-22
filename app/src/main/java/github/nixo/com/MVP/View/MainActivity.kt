package github.nixo.com.MVP.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import github.nixo.com.MVP.Present.MainPresent
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity

class MainActivity : BaseActivity<MainPresent>() {
    override fun onViewStateResotre(saveInstanceState: Bundle?) {

    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
