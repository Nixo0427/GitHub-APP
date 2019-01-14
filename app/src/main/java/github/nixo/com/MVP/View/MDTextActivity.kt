package github.nixo.com.MVP.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yanzhenjie.sofia.Sofia
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.MVP.Present.MDTextPresent
import github.nixo.com.MVP.View.adapter.ContentRepositoryAdapter
import github.nixo.com.MVP.infs.Downloadervice
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BaseActivity
import github.nixo.com.utils.DownLoadUtils
import kotlinx.android.synthetic.main.activity_mdtext.*
import kotlinx.android.synthetic.main.fragment_mine_repository.*
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.sdk15.listeners.onClick
import java.lang.StringBuilder


class MDTextActivity : BaseActivity<MDTextPresent>() ,OnRefreshListener,OnLoadmoreListener, ContentRepositoryAdapter.repositoryDirOpenListener {


    /**
     * 点击文件夹或者文件的时候，通过接口返回type类型判断是Dir还是Files ，
     * 如果是Dir就在BaseUrl的get请求后面加上子分级路径，再次请求adapter刷新
     */
    override fun open(type: String, name: String) {
        Log.e("Nixo","Adapter点击事件")
        if("dir".equals(type)){


            path = path+name+"/"
            presenter.RepositoryContent("Nixo0427","GitHub-App","master",adapter,path.toString())
        }
    }


    private var repo :String = ""
    private var username :String = ""
    private var adapter : ContentRepositoryAdapter? = null
    private var path : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sofia.with(this@MDTextActivity).statusBarLightFont()
        setContentView(R.layout.activity_mdtext)
        initSmartLaoyoutRefrsh()
        initRecyclerView()
        requestPermission(tv_mdtext)
        repo = intent.getStringExtra("repo")
        username = intent.getStringExtra("username")
        presenter.MDTextShowFuncation(repo)
        tv_title.text = "$repo"
        iv_back.onClick { finish() }
    }
    fun initRecyclerView(){
        adapter = ContentRepositoryAdapter(this,this)
        var manager = LinearLayoutManager(this)
        recyclerview_repository.layoutManager = manager
        recyclerview_repository.adapter = adapter
        presenter.RepositoryContent("Nixo0427","GitHub-App","master",adapter,path)

    }

    private fun initSmartLaoyoutRefrsh() {
        srl_content_repository.setOnRefreshListener(this)
        srl_content_repository.refreshHeader = MaterialHeader(this)
//        srl_content_repository.setOnLoadmoreListener(this)
    }
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        srl_content_repository.finishRefresh()
        presenter.MDTextShowFuncation(repo)
        presenter.RepositoryContent("Nixo0427","GitHub-App","master",adapter,path)

    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        srl_content_repository.finishLoadmore()
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


    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }

}

