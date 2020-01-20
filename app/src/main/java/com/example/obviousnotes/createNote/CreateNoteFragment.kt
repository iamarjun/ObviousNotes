package com.example.obviousnotes.createNote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.obviousnotes.MainActivity
import com.example.obviousnotes.NotesViewModel
import com.example.obviousnotes.R
import com.example.obviousnotes.model.Note
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.create_note_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : Fragment(R.layout.create_note_fragment) {

    private var title: AppCompatEditText? = null
    private var content: AppCompatEditText? = null
    private var fab: FloatingActionButton? = null
    private var bar: BottomAppBar? = null

    private lateinit var viewModel: NotesViewModel
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        title = view.title
        title?.isEnabled = true
        content = view.content
        content?.isEnabled = true

        bar = (activity as MainActivity).bar
        bar?.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE

        fab = (activity as MainActivity).fab
        fab?.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_check
            )
        )

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
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

            fab?.setOnClickListener {

                if (validate()) {

                    viewModel.addNote(Note(title?.text.toString(), content?.text.toString(),getCurrentTimeStamp()))

                    bar?.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    navController.navigate(CreateNoteFragmentDirections.actionCreateNoteFragmentToNoteDetailsFragment())

                    fab?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context!!,
                            R.drawable.ic_close
                        )
                    )

                    Toast.makeText(context, "Note created!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentTimeStamp(): String {
        val s = SimpleDateFormat("dd MMM yyyy, hh:mm aa", Locale.getDefault())
        return s.format(Date())
    }

    private fun validate(): Boolean {
        if (title?.text.toString().isEmpty()) {
            Toast.makeText(context, "Please add a title to your note.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (content?.text.toString().isEmpty()) {
            Toast.makeText(context, "Please add content to your note.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}
