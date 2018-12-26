package github.nixo.com.MVP.Present

import android.util.Log
import android.view.View
import github.nixo.com.Common.NetWork.Services.EventServce
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.View.fragment.PublicEventFragment
import github.nixo.com.github.mvp.Impl.BasePresenter
import kotlinx.android.synthetic.main.fragment_public_event.*

class PublicEventPresent : BasePresenter<PublicEventFragment>() {

    fun getPublicEvent(){
        EventServce.getNewsList(view.page)
                .subscribe({
                    Log.e("Nixo0407","${it[0].payload}")
                    view.isFirst.yes {
                        view.adapter!!.setDataList(it)
                    }.otherwise {
                        view.adapter!!.addAll(it)
                    }

                },{})
    }
}