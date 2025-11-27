package ir.safarzadehali.myapp.model.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlashCard(
    val id: Int,
    val title: String,
    val description: String,
    val publisher: String,
    val created_at: String,
    val words: List<Word>
):Parcelable