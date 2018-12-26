package github.nixo.com.MVP.View.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import cn.carbs.android.avatarimageview.library.AppCompatAvatarImageView
import github.nixo.com.Ext.loadWithGlide
import github.nixo.com.MVP.Model.MainPublicEvent
import github.nixo.com.github.R
import github.nixo.com.utils.ListBaseAdapter
import github.nixo.com.utils.SuperViewHolder

class PublicEventAdapter (context: Context): ListBaseAdapter<MainPublicEvent>(context) {
    override val layoutId: Int
        get() =  R.layout.item_public_event

    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        var headerImg = holder.getView<AppCompatAvatarImageView>(R.id.acaiv_header)
        var tvName = holder.getView<TextView>(R.id.tv_name)
        var tvTime = holder.getView<TextView>(R.id.tv_time)
        var tvContent = holder.getView<TextView>(R.id.tv_content)
        var tvType = holder.getView<TextView>(R.id.tv_type)
        var line = holder.getView<View>(R.id.line)


        headerImg.loadWithGlide(mDataList[position].actor!!.avatar_url!!,'N')
        tvName.text = mDataList[position].actor!!.login

        val source =  mDataList[position].created_at.toString() //原文本
        val pattern = """[0-9:-]+""" //正则式

        //正则表达式，筛选出最后更新时间
        var toList : List<MatchResult> = Regex(pattern).findAll(source).toList()
        var day  = toList.get(0).value
        var time  = toList.get(1).value
        tvTime.text = "$day  $time"
        tvType.text = "${mDataList[position].type}"
        if(mDataList[position].type.equals("IssueCommentEvent")){
            tvContent.text = mDataList[position].payload!!.body
        }else{
            if(mDataList[position].payload!! == null){
                tvContent.text = "${mDataList[position].type} On -> ${mDataList[position].repo!!.name}"
                return
            }else{
                if(mDataList[position].payload!!.commits == null || mDataList[position].payload!!.commits!!.isEmpty()){
                    tvContent.text = "${mDataList[position].type} On -> ${mDataList[position].repo!!.name}"
                    return
                }
                if(mDataList[position].payload!!.commits!![0] == null){
                    tvContent.visibility = View.GONE
                    line.visibility = View.GONE
                    return
                }
                tvContent.text ="${mDataList[position].payload!!.commits!![0].author!!.name} ${mDataList[position].type} : ${mDataList[position].payload!!.commits!![0].message}"
            }

        }
    }

}