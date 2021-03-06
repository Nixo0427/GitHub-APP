package github.nixo.com.MVP.View.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.github.R
import github.nixo.com.utils.ListBaseAdapter
import github.nixo.com.utils.SuperViewHolder

class MainRepositoriesAdapter(mContext : Context) : ListBaseAdapter<Repository?>(mContext) {
    override val layoutId: Int
        get() = R.layout.item_repository

    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        var title = holder.getView<TextView>(R.id.item_tv_repository_titile)
        var language = holder.getView<TextView>(R.id.item_tv_repository_languge)
        var forkLayout = holder.getView<LinearLayout>(R.id.item_ll_repository_fork)
        var fork = holder.getView<TextView>(R.id.item_tv_repository_fork)
        var update = holder.getView<TextView>(R.id.item_tv_repository_update)
        var img = holder.getView<AppCompatAvatarImageView>(R.id.item_iv_repository_img)

        Log.e("Nixo---Adapter拿没拿到View","${title==null}")
        var bean= mDataList.get(position)!!

        (bean.fork).yes {
            forkLayout.visibility = View.VISIBLE
            fork.text = "${bean.forks}"
        }.otherwise {
            fork.text = ""
            forkLayout.visibility = View.GONE
        }
//        img.loadWithGlide(bean.avatar_url)
        title.text = bean.full_name
        language.text = bean.language
        update.text = bean.updated_at
    }


}