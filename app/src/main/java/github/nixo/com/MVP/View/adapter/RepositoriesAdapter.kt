package github.nixo.com.MVP.View.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import cn.carbs.android.avatarimageview.library.AppSquareAvatarImageView
import github.nixo.com.Common.NetWork.Repository.Repository
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.Ext.otherwise
import github.nixo.com.Ext.yes
import github.nixo.com.MVP.View.MDTextActivity
import github.nixo.com.github.R
import github.nixo.com.utils.ListBaseAdapter
import github.nixo.com.utils.SuperViewHolder
import org.jetbrains.anko.activityManager
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

class RepositoriesAdapter(mContext : Activity,startType:String? ) : ListBaseAdapter<Repository?>(mContext) {

    override val layoutId: Int
        get() =R.layout.item_repository

    val startType = startType
//    var mDataList :: GitHubPaging<Repository>? = list
    
    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        var title = holder.getView<TextView>(R.id.item_tv_repository_titile)
        var language = holder.getView<TextView>(R.id.item_tv_repository_languge)
        var forkLayout = holder.getView<LinearLayout>(R.id.item_ll_repository_fork)
        var fork = holder.getView<TextView>(R.id.item_tv_repository_fork)
        var update = holder.getView<TextView>(R.id.item_tv_repository_update)
        var img = holder.getView<AppSquareAvatarImageView>(R.id.item_iv_repository_img)

//
        Log.e("Nixo---Adapter拿没拿到View","${title==null}")
        var bean= mDataList.get(position)!!
        var date  = bean.updated_at

        val source =  bean.updated_at //原文本
        val pattern = """[0-9:-]+""" //正则式

        //正则表达式，筛选出最后更新时间
        var toList : List<MatchResult> = Regex(pattern).findAll(source).toList()
        var day  = toList.get(0).value
        var time  = toList.get(1).value


        holder.itemView.onClick {
            Log.e("Nixo0407","${mDataList[position]}")
        }
        (bean.fork).yes {
            forkLayout.visibility = View.VISIBLE
            fork.text = "${bean.forks}"
        }.otherwise {
            fork.text = ""
            forkLayout.visibility = View.GONE
        }
        img.loadWithGlide(bean.owner.avatar_url,bean.owner.login.first())
        var lanuage = if(TextUtils.isEmpty(bean.language)){"UnKnow"}else{bean.language}
        title.text = bean.full_name
        language.text = if(TextUtils.isEmpty(bean.language)) "Unknown" else bean.language
        update.text = "updated for:   "+day+"  "+time
        holder.itemView.onClick {
            var intent = Intent(mContext, MDTextActivity::class.java)
            intent.putExtra("repo",bean.name)
            intent.putExtra("username",bean.owner.login)
            mContext.startActivity(intent)
        }
    }

}