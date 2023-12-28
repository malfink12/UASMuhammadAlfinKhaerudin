package com.example.uasmuhammadalfinkhaerudin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uasmuhammadalfinkhaerudin.data.local.NoteDao
import com.example.uasmuhammadalfinkhaerudin.domain.model.Note

@Database(entities = [Note::class], version=1, exportSchema=true)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}