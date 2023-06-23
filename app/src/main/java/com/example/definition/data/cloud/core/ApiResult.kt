package com.example.definition.data.cloud.core

import com.example.definition.data.cloud.model.License
import com.example.definition.data.cloud.model.Word

sealed class ApiResult(val word: Word) {

    class Success(word: Word) : ApiResult(word = word)

    object Error : ApiResult(word = Word(License("", ""), emptyList(), emptyList(), emptyList(), ""))

}