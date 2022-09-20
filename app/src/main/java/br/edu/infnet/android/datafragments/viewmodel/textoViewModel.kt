package br.edu.infnet.android.datafragments.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text
    fun changeText(_texto: String): Unit{

        _text.value = _texto
        Log.d("_text=", _text.value.toString())

    }
}