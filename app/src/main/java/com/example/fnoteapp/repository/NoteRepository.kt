package com.example.fnoteapp.repository

import com.example.fnoteapp.database.NoteDatabase
import com.example.fnoteapp.model.Note

class NoteRepository(private val db:NoteDatabase) {

    suspend fun insertNote(note:Note)=db.getNoteDao().insertNote(note)
    suspend fun updateNote(note: Note)=db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note)=db.getNoteDao().deleteNote(note)

    fun getAllNotes()=db.getNoteDao().getAllNotes()
    fun searchNote(query:String?)=db.getNoteDao().searchNote(query)
}