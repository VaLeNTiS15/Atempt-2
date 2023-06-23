package com.example.definition.data.cloud

import com.example.definition.data.cloud.core.ApiResult

class Repository {

    suspend fun getDefinition(word: String) : ApiResult {
        val retrofit = RetrofitInstance.retrofit

        return try {
            val resultWord = retrofit.getDefinition(word).body()
            if (resultWord != null) {
                ApiResult.Success(resultWord.first())
            } else {
                ApiResult.Error
            }
        } catch (e: Exception) {
            ApiResult.Error
        }
    }

}