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

    private val _bottomSheetState = MutableStateFlow(false)
    val bottomSheetState: StateFlow<Boolean> = _bottomSheetState

    fun updateIndex(type: String, index: Int) {
        _type.value = type
        _index.value = index


    }

    fun updateBottomSheetState(state:Boolean) {
        _bottomSheetState.value = state
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
      _items.removeAt(index)
    }
     fun removeItem(type:String,index:Int?){
        when (type) {
            "All" -> clearItems()
            "Single" -> removeItemAt(index!!)
        }
    }
}