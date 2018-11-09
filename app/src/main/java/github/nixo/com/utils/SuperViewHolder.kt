package github.nixo.com.utils

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * ViewHolder基类
 */
class SuperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View>

    init {
        this.views = SparseArray()
    }

    fun <T : View> getView(viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }
}
