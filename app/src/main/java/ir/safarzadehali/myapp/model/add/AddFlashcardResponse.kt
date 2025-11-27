package ir.safarzadehali.myapp.model.add

data class AddFlashcardResponse(
    val status: Boolean,
    val message: String,
    val flashcard: FlashcardData?
)