package com.example.definition.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    //Функції для роботи з базою даних: пошук, додавання, видалення слів
    @Query("SELECT * FROM word_table WHERE wordString = :wordString")
    suspend fun findWord(wordString: String): WordDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWord(word: WordDb)

    @Query("DELETE FROM word_table WHERE wordString = :wordString")
    suspend fun deleteWord(wordString: String)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<WordDb>>

}
