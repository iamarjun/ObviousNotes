package com.example.obviousnotes.noteList

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.obviousnotes.MainActivity
import com.example.obviousnotes.NotesViewModel
import com.example.obviousnotes.R
import com.example.obviousnotes.SpacesItemDecoration
import com.example.obviousnotes.createNote.CreateNoteFragmentDirections
import com.example.obviousnotes.model.Note
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notes_list_fragment.view.*


class NotesListFragment : Fragment(R.layout.notes_list_fragment),
    NotesListAdapter.OnNoteClickListener {

    private var list: RecyclerView? = null
    private var emptyList: LinearLayout? = null
    private val notesAdapter by lazy { NotesListAdapter(this) }
    private var fab: FloatingActionButton? = null
    private var bar: BottomAppBar? = null

    private lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        list = view.notes_list
        list?.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            addItemDecoration(SpacesItemDecoration(10))
            adapter = notesAdapter
        }

        emptyList = view.empty_list

        bar = (activity as MainActivity).bar
        bar?.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE

        fab = (activity as MainActivity).fab
        fab?.setOnClickListener {
            bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            navController.navigate(NotesListFragmentDirections.actionNotesListFragmentToCreateNoteFragment())
            (it as FloatingActionButton).setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_check
                )
            )
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(NotesViewModel::class.java)
            viewModel.notesList.observe(this, Observer {
                emptyList?.visibility = View.GONE
                notesAdapter.addNotes(it)
            })

        }

    }

    override fun onNoteClick(note: Note) {

        bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

        val action = NotesListFragmentDirections.actionNotesListFragmentToNoteDetailsFragment(note)
        navController.navigate(action)

        fab?.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_close
            )
        )

    }

}
