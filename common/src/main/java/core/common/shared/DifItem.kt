package core.common.shared

interface DifItem<T> {

    fun areItemsTheSame(second: T): Boolean
    fun areContentsTheSame(second: T): Boolean

}
