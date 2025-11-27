package ir.safarzadehali.myapp.repository

import ir.safarzadehali.myapp.model.add.AddFlashcardResponse
import ir.safarzadehali.myapp.model.all.FlashCardResponse
import ir.safarzadehali.myapp.model.mycard.MyFlashcardResponse
import ir.safarzadehali.myapp.retro.RetrofitInstance

class FlashCardRepository {
    suspend fun fetchFlashCards(): FlashCardResponse {
        return RetrofitInstance.api.getFlashCards()
    }

    suspend fun getFlashCards(userId: Int): MyFlashcardResponse {
        return RetrofitInstance.api.getFlashcards(userId)
    }

    suspend fun addFlashCard(userId: Int,
        title: String,
        description: String,
        english: List<String>,
        persian: List<String>
    ): AddFlashcardResponse {
        return RetrofitInstance.api.addFlashCard(userId, title, description, english, persian)
    }

}