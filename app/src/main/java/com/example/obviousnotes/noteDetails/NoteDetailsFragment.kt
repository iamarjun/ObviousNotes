package com.example.obviousnotes.noteDetails

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.obviousnotes.MainActivity
import com.example.obviousnotes.NotesViewModel
import com.example.obviousnotes.R
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.noteList.NotesListFragmentDirections
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_details_fragment.view.*


class NoteDetailsFragment : Fragment(R.layout.note_details_fragment) {

    private var timeStamp: MaterialTextView? = null
    private var title: MaterialTextView? = null
    private var content: MaterialTextView? = null
    private var fab: FloatingActionButton? = null
    private var bar: BottomAppBar? = null

    private var note: Note? = null

    private lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            note = it["Note"] as Note?
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

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

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            fab?.setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_add
                )
            )

            navController.popBackStack()
        }

        callback.isEnabled = true

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(NotesViewModel::class.java)
            viewModel.newNote.observe(it, Observer {
                title?.text = it.title
                content?.text = it.content
                timeStamp?.text = "Created on: ${it.timeStamp}"
            })
        }

        note?.let {
            title?.text = it.title
            content?.text = it.content
            timeStamp?.text = "Created on: ${it.timeStamp}"
        }

    }

}
