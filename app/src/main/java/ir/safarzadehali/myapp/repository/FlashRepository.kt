package ir.safarzadehali.myapp.repository

import ir.safarzadehali.myapp.api.ApiService
import ir.safarzadehali.myapp.model.FlashCard
import ir.safarzadehali.myapp.model.FlashCardDao
import ir.safarzadehali.myapp.model.FlashCardEntity
import ir.safarzadehali.myapp.model.WordEntity

class FlashRepository(
    private val api: ApiService,
    private val dao: FlashCardDao
) {

    /*
    suspend fun getFromServer(): List<FlashCard> {
        return api.getFlashCards()
    }\

     */

    suspend fun saveToRoom(list: List<FlashCard>) {
        dao.clearAll()
        list.forEach { fc ->
            dao.insertFlashCard(FlashCardEntity(fc.id, fc.title, fc.description))

            fc.words.forEach { w ->
                dao.insertWord(WordEntity(0, fc.id, w.english, w.persian))
            }
        }
    }

    fun getFromRoom() = dao.getFlashCardsWithWords()
}
