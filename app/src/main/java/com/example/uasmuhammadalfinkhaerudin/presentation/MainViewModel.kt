package com.example.uasmuhammadalfinkhaerudin.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmuhammadalfinkhaerudin.data.repository.NoteRepository
import com.example.uasmuhammadalfinkhaerudin.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {
    var note by mutableStateOf(Note(0,"","",""))
        private set
    var notes by mutableStateOf(listOf(Note(0,"","","")))

    val getAllNotes = repository.getAllNotes()

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note = note)
        }
    }
    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note = note)
        }
    }
    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note = note)
        }
    }
    fun getNoteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            note = repository.getNoteById(id = id)
        }
    }
    fun getNoteByHari(hari: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notes = repository.getNoteByHari(hari = hari)
        }
    }
    fun updateJadwal(newValue: String) {
        note = note.copy(jadwal = newValue)
    }
    fun updateJam(newValue: String) {
        note = note.copy(jam = newValue)
    }
    fun updateHari(newValue: String) {
        note = note.copy(hari = newValue)
    }

}