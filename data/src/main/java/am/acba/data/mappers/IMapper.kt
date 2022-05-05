package am.acba.data.mappers

interface IMapper<in T, out K> {
    fun map(model: T): K
}