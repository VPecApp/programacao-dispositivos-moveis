package com.example.cadastrocomfoto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cliente(val nome: String, val idade: Int) : Parcelable