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
        var type = ""
        var name = ""
        if(position == 0){
            type = "dir"
            name = "..."
        }else{
            type =  mDataList[position].type
            name = mDataList[position].name
        }
        if("file".equals(type)){
            imageview_type.setImageResource(R.mipmap.files)
        }else{
            imageview_type.setImageResource(R.mipmap.dirs)
        }
        tvTitle.text = name

        holder.itemView.setOnClickListener{
            if(mDataList[position].download_url == null){
                listener.open(mDataList[position].type,name,"")
            }else{
                listener.open(mDataList[position].type,name, mDataList[position].download_url)}
        }
//        holder.itemView.onClick {  }
    }


    interface repositoryDirOpenListener{

        fun open(type :String , name :String ,downloadUrl : String)
    }

}