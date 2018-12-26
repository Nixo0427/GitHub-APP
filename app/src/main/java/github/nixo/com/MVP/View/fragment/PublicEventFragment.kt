package github.nixo.com.MVP.View.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.MVP.Present.PublicEventPresent
import github.nixo.com.MVP.View.adapter.PublicEventAdapter
import github.nixo.com.github.R
import github.nixo.com.utils.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine_repository.*
import kotlinx.android.synthetic.main.fragment_public_event.*

class PublicEventFragment : BaseFragment<PublicEventPresent>() , OnRefreshListener, OnLoadmoreListener {
    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        srl_public_event.finishLoadmore()
        isFirst = false
        page ++
        presenter.getPublicEvent()
    }


    override fun setLayoutParame()
            = R.layout.fragment_public_event

    var isFirst = true
    var page : Int = 1
    var adapter : PublicEventAdapter? = null
    var layoutManager : LinearLayoutManager? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PublicEventAdapter(activity!!.baseContext)
        layoutManager = LinearLayoutManager(activity)
        srl_public_event.setOnLoadmoreListener(this)
        srl_public_event.setOnRefreshListener(this)
        rv_public_event.layoutManager = layoutManager
        rv_public_event.adapter = adapter
        presenter.getPublicEvent()
    }
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        srl_public_event.finishRefresh()
        presenter.getPublicEvent()
    }



    override fun onViewStateResotre(saveInstanceState: Bundle?) {
    }

    override fun onDestory() {
    }
}