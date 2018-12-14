package github.nixo.com.MVP.View.adapter

import android.content.Context
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AppSquareAvatarImageView
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.MVP.Model.Following
import github.nixo.com.MVP.infs.adapterOnClickListener
import github.nixo.com.github.R
import github.nixo.com.utils.ListBaseAdapter
import github.nixo.com.utils.SuperViewHolder
import kotlinx.android.synthetic.main.item_following.view.*
import org.jetbrains.anko.sdk15.listeners.onClick

class FollowingAdapter(context: Context, val onAdapter : adapterOnClickListener, override val layoutId: Int): ListBaseAdapter<Following>(context){

    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        var aiHeader  = holder.getView<AppSquareAvatarImageView>(R.id.ai_avatar)
        var tvLogin   = holder.getView<TextView>(R.id.tv_login)
        aiHeader.loadWithGlide(mDataList[position].avatar_url!!,mDataList[position].login.first())
        tvLogin.text = mDataList[position].login
        holder.itemView.onClick {
            onAdapter.onAdapterClick(mDataList[position])
        }
    }
}