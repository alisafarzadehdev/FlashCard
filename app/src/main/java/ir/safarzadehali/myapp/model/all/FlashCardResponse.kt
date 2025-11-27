package ir.safarzadehali.myapp.model.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlashCardResponse(
    val status: Boolean,
    val count: Int,
    val data: List<FlashCard>
): Parcelable