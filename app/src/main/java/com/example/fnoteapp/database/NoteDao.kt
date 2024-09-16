package com.example.fnoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fnoteapp.model.Note

@Dao
interface NoteDao {
    //here onConflict are used to replace the old data to new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //this query line basically used to declare the place of notes..
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    //this
    @Query("SELECT * FROM NOTES WHERE noteTittle LIKE :query OR noteDesc LIKE:query")
    fun searchNote(query: String?): LiveData<List<Note>>
}