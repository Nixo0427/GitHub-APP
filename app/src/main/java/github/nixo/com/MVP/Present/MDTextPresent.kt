package github.nixo.com.MVP.Present

import android.os.Environment
import android.view.View
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.Common.NetWork.Repository.RepositoryApi
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.MVP.View.MDTextActivity
import github.nixo.com.MVP.View.adapter.ContentRepositoryAdapter
import github.nixo.com.MVP.infs.Downloadervice
import github.nixo.com.github.Common.Model.AccountManager.username
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.DownLoadUtils
import kotlinx.android.synthetic.main.activity_mdtext.*

class MDTextPresent : BasePresenter<MDTextActivity>() {

    fun MDTextShowFuncation(repos: String) {
        Downloadervice.getMdTextUrl(username, repos, username).subscribe({
            Downloadervice.getMDInfo(it.download_url).subscribe({
                var utils = DownLoadUtils()
                var mMdText = utils.saveFiles(it, "${Environment.getExternalStorageDirectory()}/com.nixo.github/md/", "README2.md")
                view.tv_mdtext!!.post {
                    val spanned = MarkDown.fromMarkdown(mMdText.toString(), {
                        val drawable = view.resources.getDrawable(R.mipmap.logo2)
                        drawable.setBounds(0, 0, 400, 400)
                        drawable
                    }, view.tv_mdtext)
                    view.tv_mdtext!!.text = spanned
                }
            }, {
            })

        }, {})
    }

//    @GET("/repos/{login}/{repo}/contents/")
    fun RepositoryContent(login :String ,repo:String ,branch:String ,adapter:ContentRepositoryAdapter?,dir: String){
        RepositoryService.contentsRepositores(login,repo,dir,"master",login).subscribe({
            if (adapter != null) {
                adapter.setDataList(it)
            }
        },{})

    }

}