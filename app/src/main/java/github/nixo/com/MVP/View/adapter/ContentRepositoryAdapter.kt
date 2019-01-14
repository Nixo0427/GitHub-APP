package github.nixo.com.MVP.View.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import github.nixo.com.MVP.Model.ContentsRepository
import github.nixo.com.github.R
import github.nixo.com.utils.ListBaseAdapter
import github.nixo.com.utils.SuperViewHolder
import org.jetbrains.anko.sdk15.listeners.onClick

class ContentRepositoryAdapter(context:Context,listener :repositoryDirOpenListener) : ListBaseAdapter<ContentsRepository>(context){
    override val layoutId: Int
        get() = R.layout.adapter_content_repository

    private var listener = listener


    override fun onBindItemHolder(holder: SuperViewHolder, position: Int) {
        var tvTitle = holder.getView<TextView>(R.id.title)
        var imageview_type = holder.getView<ImageView>(R.id.imageview_type)
        if("file".equals(mDataList[position].type)){
            imageview_type.setImageResource(R.mipmap.files)
        }else{
            imageview_type.setImageResource(R.mipmap.dirs)
        }
        tvTitle.text = mDataList[position].name
        holder.itemView.setOnClickListener{listener.open(mDataList[position].type,mDataList[position].name)}
//        holder.itemView.onClick {  }
    }


    interface repositoryDirOpenListener{
        fun open(type :String , name :String )
    }

}