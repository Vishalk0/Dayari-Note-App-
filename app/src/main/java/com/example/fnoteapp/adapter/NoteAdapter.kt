package com.example.fnoteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fnoteapp.fragments.HomeFragmentDirections
import com.example.fnoteapp.model.Note
import com.example.notesroompractice.databinding.NoteLayoutBinding

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {

            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTittle == newItem.noteTittle
        }


        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
           return oldItem==newItem
        }

    }
 val differ=AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       return NoteViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
////com.example.fnoteapp.model.Note

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val correntNote=differ.currentList[position]
        holder.itemBinding.noteTitle.text=correntNote.noteTittle
        holder.itemBinding.noteDesc.text=correntNote.noteDesc
        holder.itemView.setOnClickListener {
            val direction=HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(correntNote)
            it.findNavController().navigate(direction)
        }
    }
}