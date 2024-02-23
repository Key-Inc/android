package com.keyinc.keymono.domain.paginator

interface Paginator<Key, Item> {

    suspend fun loadNextItems()

    fun reset()

}