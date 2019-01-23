package github.nixo.com.MVP.Present

import android.os.Environment
import android.view.View
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.Common.NetWork.Repository.RepositoryApi
import github.nixo.com.Common.NetWork.Repository.RepositoryService
import github.nixo.com.MVP.Model.ContentsRepository
import github.nixo.com.MVP.Model._links
import github.nixo.com.MVP.View.MDTextActivity
import github.nixo.com.MVP.View.adapter.ContentRepositoryAdapter
import github.nixo.com.MVP.infs.Downloadervice
import github.nixo.com.github.Common.Model.AccountManager.username
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.DownLoadUtils
import github.nixo.com.utils.dialog.LoadingDialog2
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.activity_mdtext.*

class MDTextPresent : BasePresenter<MDTextActivity>() {

    fun MDTextShowFuncation(repos: String) {
        Downloadervice.getMdTextUrl(username, repos, username).subscribe({
            Downloadervice.getMDInfo(it.download_url).subscribe({
                var utils = DownLoadUtils()
                var mMdText = utils.saveFiles(it, "${Environment.getExternalStorageDirectory()}/com.nixo.github/md/", "README2.md")
                var withTheme = Options.get(view.baseContext).withTheme(ColorTheme.SOLARIZED_LIGHT)
                withTheme.shortcut = false
                withTheme.animateOnHighlight = true
                withTheme.language = "md"
                withTheme.shadows
                view.code_view.setOptions(withTheme)
                //todo 栈溢出 ， 回家找原因 :c
                // we handle here instead of another method so we don't add stacks to the frame
                // which can prevent it from being able to handle StackOverflow
                view.code_view.setCode(mMdText)
            }, {
            })

        }, {})
    }

    //    @GET("/repos/{login}/{repo}/contents/")
    fun RepositoryContent(login :String ,repo:String ,branch:String ,adapter:ContentRepositoryAdapter?,dir: String){
        var loadingDialog2 = LoadingDialog2().getLoadDialog(view.supportFragmentManager)
        RepositoryService.contentsRepositores(login,repo,dir,"master",login).subscribe({
            //            view.srl_content_repository.autoRefresh()
            if (adapter != null) {
                it.add(0, ContentsRepository("","","",0,"","","","123","",_links = _links("","","")))
                loadingDialog2.dismiss()
                adapter.setDataList(it)
            }
        },{
            loadingDialog2
        })

    }

}