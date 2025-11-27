package ir.safarzadehali.myapp.model.all

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val english: String,
    val persian: String
): Parcelable