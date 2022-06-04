package com.github.armkrtchyan.data

interface IMapper<in T, out K> {
    fun map(model: T): K
}