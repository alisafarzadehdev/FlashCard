package ir.safarzadehali.myapp.model.add

data class FlashcardData(
    val id: Int,
    val title: String,
    val description: String,
    val word_count: Int,
    val user_id: Int
)