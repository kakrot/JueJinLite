package cn.juejin.app.lite.common

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView

open class RecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var canLoadMore = true
    var onLoadMore: () -> Unit = {}
    private val models = arrayListOf<T>()
    private val itemViewDelegates = SparseArray<ItemViewDelegate<T>>()
    private var inflater: LayoutInflater? = null

    fun addDelegate(delegate: ItemViewDelegate<T>) {
        itemViewDelegates.put(delegate.getItemViewType(), delegate)
    }

    fun submitList(elements: List<T>) {
        if (elements.isEmpty()) {
            return
        }
        val size = models.size
        models.addAll(elements)
        if (size == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(size, elements.size)
        }
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun getItem(position: Int): T? {
        if (position >= 0 && position < models.size) {
            return models[position]
        }
        return null
    }

    override fun getItemViewType(position: Int): Int {
        val size = itemViewDelegates.size()
        require(size > 0) { "itemViewDelegates is not empty" }
        var viewType = -1
        val item = getItem(position)
        itemViewDelegates.forEach { key, value ->
            if (value.isViewFromObject(item)) {
                viewType = key
                return@forEach
            }
        }
        require(viewType >= 0) { "cannot support this viewType at $position" }
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate = itemViewDelegates[viewType]
        if (null == inflater) {
            inflater = LayoutInflater.from(parent.context)
        }
        val view = inflater!!.inflate(delegate.getItemViewType(),parent,false)
        delegate.onViewCreated(view)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        itemViewDelegates[holder.itemViewType].onBindView(position, getItem(position))
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        val position = holder.adapterPosition
        val item = getItem(position)
        itemViewDelegates[holder.itemViewType].onViewAttachToWindow(position, item)
        if (canLoadMore && position == models.size - 2) {
            onLoadMore()
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        val position = holder.adapterPosition
        val item = getItem(position)
        itemViewDelegates[holder.itemViewType].onViewDetachFromWindow(position, item)
    }
}

interface ItemViewDelegate<T> {

    fun isViewFromObject(model: T?): Boolean

    @LayoutRes
    fun getItemViewType(): Int

    fun onViewCreated(view: View)

    fun onBindView(position: Int, model: T?)

    fun onViewAttachToWindow(position: Int, model: T?) {
    }

    fun onViewDetachFromWindow(position: Int, model: T?) {
    }

}