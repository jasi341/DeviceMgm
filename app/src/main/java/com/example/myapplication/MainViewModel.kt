package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel(){



    private val _type = MutableStateFlow("")
    val type: StateFlow<String> = _type

    private val _index = MutableStateFlow(0)
    val index: StateFlow<Int> = _index

    private val _items = mutableStateListOf<Model>()
    val items: List<Model> = _items

    fun removeType(type: String, index: Int) {
        _type.value = type
        _index.value = index

        when (type) {
            "All" -> clearItems()
            "Single" -> removeItemAt(index)
        }
    }

    init {

        fetchItems()
    }

    private fun fetchItems() {
        _items.addAll(listDevice)
    }
    private fun clearItems() {
        _items.clear()
    }

    private fun removeItemAt(index: Int) {
        if (index in _items.indices) {
            _items.removeAt(index)
        }
    }
}