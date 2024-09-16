package com.example.fnoteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fnoteapp.model.Note
import com.example.fnoteapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app:Application,private val noteRepository: NoteRepository):AndroidViewModel(app) {

    fun addNote(note:Note)=
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun deleteNote(note:Note)=
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    fun updateNote(note:Note)=
        viewModelScope.launch {
            viewModelScope.launch {
                noteRepository.updateNote(note)
            }
        }
    fun getAllNotes()=noteRepository.getAllNotes()
    fun searchNote(query:String)=noteRepository.searchNote(query)
}