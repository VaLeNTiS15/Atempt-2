package com.example.definition.presentation.favorite

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.definition.data.cache.WordDatabase
import com.example.definition.data.cache.WordDb

class FavoriteViewModel : ViewModel() {

    fun observe(context: Context, owner: LifecycleOwner, observer: Observer<in List<WordDb>>) {
        val wordDao = WordDatabase.getDatabase(context).wordDao()
        wordDao.readAllData().observe(owner, observer)
    }

}