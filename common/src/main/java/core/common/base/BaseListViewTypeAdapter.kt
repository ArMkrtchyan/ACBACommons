package core.common.base

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import core.common.shared.getDiffCallback

abstract class BaseListViewTypeAdapter<T : Any>(callback: DiffUtil.ItemCallback<T> = getDiffCallback()) : ListAdapter<T, BaseViewHolder>(callback) {
    open val isRecyclable: Boolean = true
    protected lateinit var mContext: Context

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setIsRecyclable(isRecyclable)
        holder.onBind()
    }

}