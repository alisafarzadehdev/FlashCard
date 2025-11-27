package ir.safarzadehali.myapp.model.mycard

data class MyFlashcardResponse(
    val status: Boolean,
    val count: Int,
    val data: List<MyFlashcardData>
)