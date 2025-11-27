package ir.safarzadehali.myapp.model.mycard

data class MyFlashcardData(
    val flash_id: Int,
    val title: String,
    val words: List<WordItem>
)