package ir.safarzadehali.myapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FlashCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashCard(flashCard: FlashCardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    @Transaction
    @Query("SELECT * FROM flashcards")
    fun getFlashCardsWithWords(): LiveData<List<FlashCardWithWords>>

    @Query("DELETE FROM flashcards")
    suspend fun clearFlashCards()

    @Query("DELETE FROM words")
    suspend fun clearWords()

    @Transaction
    suspend fun clearAll() {
        clearWords()
        clearFlashCards()
    }
}
