package com.example.obviousnotes.noteDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.obviousnotes.BaseFragment
import com.example.obviousnotes.R
import com.example.obviousnotes.databinding.NoteDetailsFragmentBinding
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.util.viewBinding
import com.google.android.material.bottomappbar.BottomAppBar

class NoteDetailsFragment : BaseFragment() {

    private val binding: NoteDetailsFragmentBinding by viewBinding(NoteDetailsFragmentBinding::bind)

    private var note: Note? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            note = it["Note"] as Note?
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SCALE

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_close
            )
        )

        fab.setOnClickListener {
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            findNavController().navigate(NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNotesListFragment())
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_add
                )
            )
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_add
                )
            )

            findNavController().popBackStack()
        }

        callback.isEnabled = true

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel.newNote.observe(it, {
                binding.title.text = it.title
                binding.content.text = it.content
                binding.timeStamp.text = "Created on: ${it.timeStamp}"
            })
        }

        note?.let {
            binding.title.text = it.title
            binding.content.text = it.content
            binding.timeStamp.text = "Created on: ${it.timeStamp}"
        }

    }

}
