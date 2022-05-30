package am.acba.data

interface IMapper<in T, out K> {
    fun map(model: T): K
}