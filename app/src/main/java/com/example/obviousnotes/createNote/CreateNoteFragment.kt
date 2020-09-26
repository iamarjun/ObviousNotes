package com.example.obviousnotes.createNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.obviousnotes.BaseFragment
import com.example.obviousnotes.R
import com.example.obviousnotes.databinding.CreateNoteFragmentBinding
import com.example.obviousnotes.model.Note
import com.example.obviousnotes.util.viewBinding
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CreateNoteFragment : BaseFragment() {

    private val binding: CreateNoteFragmentBinding by viewBinding(CreateNoteFragmentBinding::bind)
    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.isEnabled = true
        binding.content.isEnabled = true

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE


        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_check
            )
        )

        fab.setOnClickListener {

            if (validate()) {

                val note = Note(
                    title = binding.title.text.toString(),
                    content = binding.content.text.toString(),
                    timeStamp = getCurrentTimeStamp()
                )

                viewModel.addNote(note)

                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                val action =
                    CreateNoteFragmentDirections.actionCreateNoteFragmentToNoteDetailsFragment(
                        note
                    )
                findNavController().navigate(action)

                Toast.makeText(context, "Note created!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentTimeStamp(): String {
        val s = SimpleDateFormat("dd MMM yyyy, hh:mm aa", Locale.getDefault())
        return s.format(Date())
    }

    private fun validate(): Boolean {
        if (binding.title.text.toString().isEmpty()) {
            Toast.makeText(context, "Please add a title to your note.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.content.text.toString().isEmpty()) {
            Toast.makeText(context, "Please add content to your note.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}
