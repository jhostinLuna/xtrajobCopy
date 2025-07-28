package com.droidper.xtrajob.core.common

interface Mapper<I,O> {
    fun map(input: I): O
    fun mapList(input: List<I>): List<O> {
        return input.map { map(it) }
    }
}