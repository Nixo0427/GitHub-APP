package github.nixo.com.utils

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import github.nixo.com.github.Ext.APP
import github.nixo.com.github.Ext.AppContext

abstract class NixoAdapter<E> : RecyclerView.Adapter<NixoAdapter.ViewHolder>() {

    var list : MutableList<E>? = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        var view : View? = null
        var layout = oncreate(p1)
        if(view == null){
            view = LayoutInflater.from(AppContext).inflate(layout,p0,false)
        }
        return ViewHolder(view!!)
    }

    fun setDataList(list:MutableList<E>){
        clear()
        this.list = list
        notifyDataSetChanged()

    }
    fun clear(){
        list!!.clear()
    }

    override fun getItemCount(): Int {
        list!!.size
    }

    abstract fun oncreate(p1: Int):Int

    abstract fun bindView(p0:ViewHolder,p1: Int)

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        bindView(p0,p1)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}