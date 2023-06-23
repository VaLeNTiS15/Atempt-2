package com.example.definition.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.definition.data.cloud.Repository
import com.example.definition.data.cloud.core.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val repository = Repository()
    val apiResultLiveData = MutableLiveData<ApiResult>()
    fun getDefinition (word: String) {
        viewModelScope.launch (Dispatchers.IO){
            val result = repository.getDefinition(word)
            apiResultLiveData.postValue(result)
        }
    }
}