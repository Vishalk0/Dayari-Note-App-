package com.example.fnoteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fnoteapp.MainActivity
import com.example.fnoteapp.adapter.NoteAdapter
import com.example.fnoteapp.model.Note
import com.example.fnoteapp.viewModel.NoteViewModel
import com.example.notesroompractice.R
import com.example.notesroompractice.databinding.FragmentHomeBinding

// here I am used fragment layout to extend in homefragment and used search view and menu provider
class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
    MenuProvider {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!
    //here declare the note view model and note adapter..
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

//here oncreate view are used to create layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //here binding the home binding in fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
//on view create are use to declare the button and all components
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel = (activity as MainActivity).noteViewModel
        setUpHomeRecyclerView()
        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }
    }

    private fun updateUi(note: List<Note>) {
        if (note != null) {

            if (note.isNotEmpty()) {
                binding.emptyNotesImage.visibility = View.GONE

            } else {

                binding.emptyNotesImage.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpHomeRecyclerView() {

        noteAdapter = NoteAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUi(note)
            }
        }
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query"
        notesViewModel.searchNote(searchQuery).observe(this) { list ->
            noteAdapter.differ.submitList(list)

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding=null
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home1, menu)
        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }


    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}
