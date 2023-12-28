package com.example.uasmuhammadalfinkhaerudin.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val jadwal: String = "",
    val jam: String = "",
    val hari: String = ""
)
