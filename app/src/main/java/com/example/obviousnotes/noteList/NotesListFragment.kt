package com.example.obviousnotes.noteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.obviousnotes.BaseFragment
import com.example.obviousnotes.R
import com.example.obviousnotes.SpacesItemDecoration
import com.example.obviousnotes.databinding.NotesListFragmentBinding
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.util.viewBinding
import com.google.android.material.bottomappbar.BottomAppBar

class NotesListFragment : BaseFragment(),
    NotesListAdapter.OnNoteClickListener {

    private val binding: NotesListFragmentBinding by viewBinding(NotesListFragmentBinding::bind)

    private val notesAdapter by lazy { NotesListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesList.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            addItemDecoration(SpacesItemDecoration(10))
            adapter = notesAdapter
        }

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_add
            )
        )

        fab.setOnClickListener {
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            findNavController().navigate(NotesListFragmentDirections.actionNotesListFragmentToCreateNoteFragment())
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_check
                )
            )
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel.notesList.observe(viewLifecycleOwner, {
                binding.emptyList.visibility = View.GONE
                notesAdapter.addNotes(it)
            })

        }

    }

    override fun onNoteClick(note: Note) {

        bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

        val action = NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(note)
        findNavController().navigate(action)

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_close
            )
        )

    }

}
