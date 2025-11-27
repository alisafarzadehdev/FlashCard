package ir.safarzadehali.myapp.model.complete

class FlashCardResponse (
    val code: Int,
    val status: String,
    val data: FlashCardData
){

}
/*
data class FlashCardResponse(
    val code: Int,
    val status: String,
    val data: FlashCardData
)

data class FlashCardData(
    val count: Int,
    val items: List<FlashCardItem>
)

data class FlashCardItem(
    val flash_id: Int,
    val title: String,
    val words: List<FlashWord>
)

data class FlashWord(
    val english: String,
    val persian: String
)

 */