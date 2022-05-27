package com.example.githubuserapp.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserGithub(
    val name: String,
    val username: String,
    val company: String,
    val photo: Int,
    val location: String,
    val repository: String,
    val followers: String,
    val following: String,
    val url: String,
): Parcelable