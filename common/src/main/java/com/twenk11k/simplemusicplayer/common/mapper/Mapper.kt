package com.twenk11k.simplemusicplayer.common.mapper

interface Mapper<I, O> {

    fun from(i: I): O

    fun to(o: O): I

    fun fromList(list: List<I>): List<O> {
        return list.mapNotNull { from(it) }
    }
}
