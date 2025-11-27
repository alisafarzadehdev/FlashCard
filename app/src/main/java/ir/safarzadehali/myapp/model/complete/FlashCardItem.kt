package ir.safarzadehali.myapp.model.complete

data class FlashCardItem(
    val flash_id: Int,
    val title: String,
    val words: List<FlashWord>
)