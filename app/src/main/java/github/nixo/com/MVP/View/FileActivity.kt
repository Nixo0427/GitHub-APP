package github.nixo.com.MVP.View

import android.os.Bundle
import com.yanzhenjie.sofia.Sofia
import github.nixo.com.MVP.Present.FilePresent
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity

class FileActivity : BaseActivity<FilePresent>() {
    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Sofia.with(this@FileActivity).statusBarLightFont()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        var stringExtra = intent.getStringExtra("fileurl")
        presenter.MDTextShowFuncation(stringExtra)


    }
}
