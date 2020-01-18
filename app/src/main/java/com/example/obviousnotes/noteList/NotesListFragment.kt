package com.example.obviousnotes.noteList

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.obviousnotes.MainActivity
import com.example.obviousnotes.NotesViewModel
import com.example.obviousnotes.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notes_list_fragment.view.*


class NotesListFragment : Fragment(R.layout.notes_list_fragment) {

    private var list: RecyclerView? = null
    private val adapter by lazy { NotesListAdapter() }
    private var fab: FloatingActionButton? = null
    private var bar: BottomAppBar? = null


    companion object {
        fun newInstance() = NotesListFragment()
    }

    private lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        list = view.notes_list
        list?.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        list?.adapter = adapter

        bar = (activity as MainActivity).bar
        bar?.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE

        fab = (activity as MainActivity).fab
        fab?.setOnClickListener {
            bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            navController.navigate(R.id.action_notesListFragment_to_createNoteFragment)
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
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
