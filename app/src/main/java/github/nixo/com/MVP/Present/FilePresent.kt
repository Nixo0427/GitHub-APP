package github.nixo.com.MVP.Present

import android.os.Environment
import com.zzhoujay.markdown.MarkDown
import github.nixo.com.MVP.View.FileActivity
import github.nixo.com.MVP.infs.Downloadervice
import github.nixo.com.github.Common.Model.AccountManager
import github.nixo.com.github.R
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.DownLoadUtils
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import io.github.kbiakov.codeview.highlight.Font
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.activity_mdtext.*

class FilePresent : BasePresenter<FileActivity>(){
    fun MDTextShowFuncation(url :String ) {
        Downloadervice.getMDInfo(url).subscribe({
            var utils = DownLoadUtils()
            var mMdText = utils.saveFiles(it, "${Environment.getExternalStorageDirectory()}/com.nixo.github/md/", "README2.md")
//            view.tv_code_text.text = mMdText
            var withTheme = Options.get(view.baseContext).withTheme(ColorTheme.MONOKAI)
            withTheme.shortcut = false
            withTheme.animateOnHighlight = true
            withTheme.language = "kt"
            withTheme.shadows
            view.code_view.setOptions(withTheme)
            view.code_view.setCode(mMdText)
        }, {
        })
    }
}