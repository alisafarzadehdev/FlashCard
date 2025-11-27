package ir.safarzadehali.myapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashCardEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String
)

