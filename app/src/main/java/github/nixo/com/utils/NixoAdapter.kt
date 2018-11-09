package github.nixo.com.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.nixo.com.github.Ext.APP
import github.nixo.com.github.Ext.AppContext

abstract class NixoAdapter<E> : RecyclerView.Adapter<SuperViewHolder>() {

    var list : MutableList<E>? = ArrayList()


    fun setDataList(list:MutableList<E>){
        clear()
        this.list = list
        notifyDataSetChanged()

    }
    fun clear(){
        list!!.clear()
    }

    override fun getItemCount(): Int {
       return list!!.size
    }
//    fun getView ():View{
//
//    }
    abstract fun oncreate(p1: Int):Int

//    abstract fun bindView(p0:ViewHolder,p1: Int)




}