package com.example.fnoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fnoteapp.model.Note

//this ano are used to declare the database..
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    companion object{
        @Volatile
        private var instance:NoteDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context:Context)= instance ?:
        synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance=it
            }

        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()

    }

}