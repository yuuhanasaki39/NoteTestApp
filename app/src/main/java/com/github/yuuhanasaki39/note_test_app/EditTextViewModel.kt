package com.github.yuuhanasaki39.note_test_app

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditTextViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _textState = MutableStateFlow("hoge")
    val textState = _textState.asStateFlow()

    // データの更新
    fun updateText(newText: String) {
        _textState.value = newText
    }
}