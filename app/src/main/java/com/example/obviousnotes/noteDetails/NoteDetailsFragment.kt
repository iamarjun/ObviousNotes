package com.example.obviousnotes.noteDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.obviousnotes.BaseFragment
import com.example.obviousnotes.R
import com.example.obviousnotes.databinding.NoteDetailsFragmentBinding
import com.example.obviousnotes.util.viewBinding
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailsFragment : BaseFragment() {

    private val binding: NoteDetailsFragmentBinding by viewBinding(NoteDetailsFragmentBinding::bind)
    private val args: NoteDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(args.note) {
            binding.title.text = title
            binding.content.text = content
            binding.timeStamp.text = "Created on: $timeStamp"
        }

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SCALE

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_close
            )
        )

        fab.setOnClickListener {
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            val action =
                NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNotesListFragment()
            findNavController().navigate(action)
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

}
