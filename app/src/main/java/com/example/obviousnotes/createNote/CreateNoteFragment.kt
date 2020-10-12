package com.example.obviousnotes.createNote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
    private val args: CreateNoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.isEnabled = true
        binding.content.isEnabled = true

        bar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE

        args.note?.let {
            binding.title.setText(it.title)
            binding.content.setText(it.content)
        }

        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_check
            )
        )

        fab.setOnClickListener {

            if (validate()) {

                args.note?.let {
                    it.apply {
                        title = binding.title.text.toString()
                        content = binding.content.text.toString()
                        timeStamp = getCurrentTimeStamp()
                    }
                    viewModel.updateNote(it)
                } ?: run {
                    val note = Note(
                        title = binding.title.text.toString(),
                        content = binding.content.text.toString(),
                        timeStamp = getCurrentTimeStamp(),
                        userId = viewModel.currentUser.id
                    )

                    viewModel.addNote(note)
                }

                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                val action =
                    CreateNoteFragmentDirections.actionCreateNoteFragmentToNotesListFragment()
                findNavController().navigate(action)
            }
        }

        bar.setNavigationOnClickListener {
            logout(this@CreateNoteFragment)
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
