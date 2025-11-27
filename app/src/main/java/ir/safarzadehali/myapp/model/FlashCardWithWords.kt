package ir.safarzadehali.myapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class FlashCardWithWords(
    @Embedded val flashCard: FlashCardEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "flashCardId"
    )
    val words: List<WordEntity>
)
