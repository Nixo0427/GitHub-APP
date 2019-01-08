package github.nixo.com.MVP.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.MVP.Present.MDTextPresent
import github.nixo.com.MVP.infs.Downloadervice
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.utils.DownLoadUtils
import kotlinx.android.synthetic.main.activity_mdtext.*
import org.jetbrains.anko.sdk15.listeners.onClick


class MDTextActivity : BaseActivity<MDTextPresent>() {
    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }


    private var repo :String = ""
    private var username :String = ""
    private var url :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mdtext)
        requestPermission(tv_mdtext)
        repo = intent.getStringExtra("repo")
        username = intent.getStringExtra("repo")
//        MDTextShowFuncation(url)
        MDTextShowFuncation()
        ll_md_loading_fail.onClick { MDTextShowFuncation() }
    }


    fun MDTextShowFuncation(){
        Downloadervice.getMdTextUrl(username,repo).subscribe({url = it.url})
        Downloadervice.allFollowing(url).subscribe({
            ll_md_loading_fail.visibility = View.GONE
            var utils = DownLoadUtils()
            var mMdText = utils.saveFiles(it, "${Environment.getExternalStorageDirectory()}/com.nixo.github/md/", "README.md")
            tv_mdtext!!.post {
                val spanned = MarkDown.fromMarkdown(mMdText.toString(), {
                    val drawable = resources.getDrawable(R.mipmap.logo2)
                    drawable.setBounds(0, 0, 400, 400)
                    drawable
                }, tv_mdtext)
                tv_mdtext!!.text = spanned
            }
        },{
            ll_md_loading_fail.visibility = View.VISIBLE
        })
    }

    fun requestPermission(view: View) {
        val checkSelfPermission = ContextCompat.checkSelfPermission(this@MDTextActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
        } else {
            //requset permission
            ActivityCompat.requestPermissions(this@MDTextActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            }else{
                //todo:permission denied
            }
        }
    }

}

