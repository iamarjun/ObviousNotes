package com.example.obviousnotes.noteDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.obviousnotes.MainActivity
import com.example.obviousnotes.NotesViewModel
import com.example.obviousnotes.R
import com.example.obviousnotes.noteList.NotesListFragmentDirections
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_details_fragment.view.*


class NoteDetailsFragment : Fragment(R.layout.note_details_fragment) {

    companion object {
        fun newInstance() =
            NoteDetailsFragment()
    }

    private var back: AppCompatImageView? = null
    private var timeStamp: MaterialTextView? = null
    private var title: MaterialTextView? = null
    private var content: MaterialTextView? = null
    private var fab: FloatingActionButton? = null
    private var bar: BottomAppBar? = null

    private lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        back = view.back
        timeStamp = view.timeStamp
        title = view.title
        content = view.content

        bar = (activity as MainActivity).bar
        bar?.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SCALE

        fab = (activity as MainActivity).fab
        fab?.setOnClickListener {
            bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            navController.navigate(NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNotesListFragment())
            (it as FloatingActionButton).setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_add
                )
            )
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(NotesViewModel::class.java)
            viewModel.newNote.observe(it, Observer {
                title?.text = it.title
                content?.text = it.content
                timeStamp?.text = it.timeStamp
            })
        }

    }

}
