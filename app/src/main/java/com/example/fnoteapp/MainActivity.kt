package com.example.fnoteapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fnoteapp.database.NoteDatabase
import com.example.fnoteapp.repository.NoteRepository
import com.example.fnoteapp.viewModel.NoteViewModel
import com.example.fnoteapp.viewModel.NoteViewModelFactory
import com.example.notesroompractice.R

class MainActivity : AppCompatActivity() {
 lateinit var noteViewModel: NoteViewModel
 //override fun start...
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
     setupViewModel()

    }
    //override fun end...
    //here view model intialized.
    private fun setupViewModel(){
        val noteRepository=NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory=NoteViewModelFactory(application,noteRepository)
        noteViewModel=ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]
    }
}