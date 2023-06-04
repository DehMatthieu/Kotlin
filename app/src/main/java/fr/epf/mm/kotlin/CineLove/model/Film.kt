package fr.epf.mm.kotlin.CineLove.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


enum class Gender {
    MAN, WOMAN
}

/**
 *
 * Doc: https://developer.android.com/kotlin/parcelize?hl=fr
 *
 */
@Parcelize
data class Film(

    val title: String,
    val overview: String,
    val releaseDate: Date,
    val ID: Int,
    val rating: Float,
    val favorite: Boolean,
    val picture: String,
) : Parcelable {

}
