package com.example.finalsubmissionbasicandroiddicoding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Airlines (
    val name: String,
    val description: String,
    val photo: Int,
    val book: String,
    val detail: String
): Parcelable