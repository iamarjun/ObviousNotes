package com.example.obviousnotes.createNote

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.obviousnotes.R


class CreateNoteFragment : Fragment(R.layout.create_note_fragment) {

    companion object {
        fun newInstance() =
            CreateNoteFragment()
    }

//    private lateinit var viewModel: CreateNoteViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
