package com.example.definition.presentation.definition

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.definition.data.cache.WordDao
import com.example.definition.data.cache.WordDatabase
import com.example.definition.data.cache.WordDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Random

class DefinitionViewModel : ViewModel() {

    val wordLiveData = MutableLiveData<WordDb?>()

    private lateinit var wordDao: WordDao

    fun init(context: Context) {
        wordDao = WordDatabase.getDatabase(context).wordDao()
    }

    fun getWord(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = wordDao.findWord(word)
            wordLiveData.postValue(result)
        }
    }

    fun deleteWord(wordString: String) {
        viewModelScope.launch {
            wordDao.deleteWord(wordString)
        }
    }

    fun saveWord(wordString: String) {
        viewModelScope.launch {
            wordDao.addWord(WordDb(Random().nextInt(), wordString))
        }
    }

}