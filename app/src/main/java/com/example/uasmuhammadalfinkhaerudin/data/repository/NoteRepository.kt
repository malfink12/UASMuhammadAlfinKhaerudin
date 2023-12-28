package com.example.uasmuhammadalfinkhaerudin.data.repository

import com.example.uasmuhammadalfinkhaerudin.data.local.NoteDao
import com.example.uasmuhammadalfinkhaerudin.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val dao: NoteDao
) {
    suspend fun insertNote(note: Note): Unit = dao.insertNote(note = note)

    suspend fun updateNote(note: Note): Unit = dao.updateNote(note = note)

    suspend fun deleteNote(note: Note): Unit = dao.deleteNote(note = note)

    suspend fun getNoteById(id: Int): Note = dao.getNoteById(id = id)

    suspend fun getNoteByHari(hari: String): List<Note> = dao.getNoteByHari(hari = hari)

    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()
}