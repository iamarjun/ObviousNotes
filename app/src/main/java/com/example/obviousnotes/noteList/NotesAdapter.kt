package com.example.obviousnotes.noteList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.obviousnotes.R
import com.example.obviousnotes.databinding.LayoutNoteItemBinding
import com.example.obviousnotes.model.Note

class NotesAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_note_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NotesViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Note>) {
        differ.submitList(list)
    }

    class NotesViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = LayoutNoteItemBinding.bind(itemView)

        fun bind(item: Note) {
            with(binding) {
                root.setOnClickListener {
                    interaction?.onItemSelected(adapterPosition, item)
                }

                title.text = item.title
                content.text = item.content
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Note)
    }
}

