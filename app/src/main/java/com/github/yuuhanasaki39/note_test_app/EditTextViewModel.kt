package com.github.yuuhanasaki39.note_test_app

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditTextViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _textState = MutableStateFlow(savedStateHandle["key"] ?: "null")
    val textState = _textState.asStateFlow()

    // データの更新
    fun updateText(newText: String) {
        savedStateHandle["key"] = newText
        _textState.value = newText
    }
}