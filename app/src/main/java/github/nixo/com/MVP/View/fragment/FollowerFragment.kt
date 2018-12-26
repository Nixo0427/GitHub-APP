package github.nixo.com.MVP.View.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.MVP.Model.Following
import github.nixo.com.MVP.Present.auth.FollowerPresent
import github.nixo.com.MVP.View.adapter.FollowingAdapter
import github.nixo.com.MVP.View.auth.UserActivity
import github.nixo.com.MVP.infs.adapterOnClickListener
import github.nixo.com.github.Ext.AppContext
import github.nixo.com.github.R
import github.nixo.com.utils.dialog.DialogBuilder
import github.nixo.com.utils.dialog.LoadingDialog
import github.nixo.com.utils.mvp.Impl.BaseFragment
import kotlinx.android.synthetic.main.fragment_follower.*
import org.jetbrains.anko.sdk15.listeners.onClick

class FollowerFragment : BaseFragment<FollowerPresent>()
        , OnRefreshListener, adapterOnClickListener, DialogBuilder.DialogViewCallInterface{


    var isFirst = true
    var adapter : FollowingAdapter? = null
    var layoutManager: GridLayoutManager? = null
    var dialog: LoadingDialog? = null
    var data: Following? = null
    var build : DialogBuilder.publicDialog? = null
    var activity : UserActivity?= null
    override fun setLayoutParame(): Int {
        return R.layout.fragment_follower
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.activity = getActivity() as UserActivity
        adapter = FollowingAdapter(AppContext,this@FollowerFragment,R.layout.item_following)
        layoutManager = GridLayoutManager(activity,2)
        srl_user_follower.setOnRefreshListener(this)
        rv_user_follower.layoutManager = layoutManager
        rv_user_follower.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        presenter.getFollowing()
    }





    override fun onRefresh(refreshlayout: RefreshLayout?) {
        srl_user_follower!!.finishRefresh()
        isFirst = true
        presenter.getFollowing()
    }
    override fun onAdapterClick(data: Any) {
        var data : Following = data as Following
        this.data = data
        build = DialogBuilder()
                .setLayout(activity!!, R.layout.dialog_following)
                .setViewCallBack(this@FollowerFragment)
                .show()
                .build()

    }

    override fun ViewBack(view: View) {
        var tvPage = view.findViewById<TextView>(R.id.tv_page)
        var tvUnFollowing = view.findViewById<TextView>(R.id.tv_unfollowing)
        tvUnFollowing.visibility = View.GONE
        var aviHeader = view.findViewById<AppCompatAvatarImageView>(R.id.avi_header)
        var tvUserName = view.findViewById<TextView>(R.id.tv_username)
        tvUserName.text = data!!.login
        aviHeader.loadWithGlide(data!!.avatar_url,data!!.login.first())
        tvPage.onClick {
            var paramer = Bundle()
            paramer.putString("newUser",data!!.login)
            actionWithParamer(UserActivity::class.java,paramer)
            activity!!.finish()
        }


    }



    override fun onDestory() {
    }
    override fun onViewStateResotre(saveInstanceState: Bundle?) {}


}