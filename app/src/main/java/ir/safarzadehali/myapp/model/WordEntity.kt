package ir.safarzadehali.myapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val wordId: Int = 0,
    val flashCardId: Int,
    val eng: String,
    val per: String
)