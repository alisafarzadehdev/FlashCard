package ir.safarzadehali.myapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlashCard(
    val id: Int,
    val title: String,
    val description: String,
    val words: ArrayList<Word>
): Parcelable