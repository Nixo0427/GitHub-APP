package github.nixo.com.MVP.Present

import android.util.Log
import github.nixo.com.Common.NetWork.Star.StarService
import github.nixo.com.MVP.View.fragment.StarFragment
import github.nixo.com.github.mvp.Impl.BasePresenter
import github.nixo.com.utils.mvp.Impl.BaseFragment

class StarPrsent : BasePresenter<StarFragment>() {

    fun getStarListRepoitory(userName : String,page:Int){
        StarService.listRepositoryBeginStar(userName,page)
                .subscribe({
                    Log.e("Nixo----Star",it.toString())
                    view.initReposition(it)
                },{})
    }

}