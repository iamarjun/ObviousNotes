package com.example.obviousnotes.noteList

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.obviousnotes.BaseFragment
import com.example.obviousnotes.R
import com.example.obviousnotes.databinding.NotesListFragmentBinding
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.util.SpacesItemDecoration
import com.example.obviousnotes.util.viewBinding
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NotesListFragment : BaseFragment() {
    private val binding: NotesListFragmentBinding by viewBinding(NotesListFragmentBinding::bind)

    private val notesAdapter by lazy {
        NotesAdapter(object : NotesAdapter.Interaction {
            override fun onItemSelected(position: Int, item: Note) {
                Timber.d("$item at $position")
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

                val action =
                    NotesListFragmentDirections.actionNotesListFragmentToCreateNoteFragment(item)
                findNavController().navigate(action)
            }

            override fun onItemLongPress(position: Int, item: Note) {
                Timber.d("$item at $position deleted")
                viewModel.deleteNote(item)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.isVisible = true
        bar.isVisible = true

        binding.notesList.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            addItemDecoration(SpacesItemDecoration(10))
            adapter = notesAdapter
        }

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE
        bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_add
            )
        )

        fab.setOnClickListener {
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            findNavController().navigate(
                NotesListFragmentDirections.actionNotesListFragmentToCreateNoteFragment(
                    null
                )
            )
        }

        viewModel.notesList.observe(viewLifecycleOwner, {
            binding.emptyList.isVisible = it.isEmpty()
            notesAdapter.submitList(it)
        })

        bar.setNavigationOnClickListener {
            logout(this@NotesListFragment)
        }

    }
}
