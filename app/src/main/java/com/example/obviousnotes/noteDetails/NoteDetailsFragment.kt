package com.example.obviousnotes.noteDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.example.obviousnotes.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.create_note_fragment.view.*


class NoteDetailsFragment : Fragment(R.layout.create_note_fragment) {

    companion object {
        fun newInstance() =
            NoteDetailsFragment()
    }

    private var back: AppCompatImageView? = null
    private var timeStamp: MaterialTextView? = null
    private var title: AppCompatEditText? = null
    private var content: AppCompatEditText? = null

//    private lateinit var viewModel: NoteDetailsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back = view.back
        timeStamp = view.timeStamp
        title = view.title
        title?.isEnabled = false
        content = view.content
        content?.isEnabled = false

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(NoteDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
