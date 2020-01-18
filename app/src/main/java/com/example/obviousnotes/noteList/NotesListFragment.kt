package com.example.obviousnotes.noteList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.obviousnotes.R
import kotlinx.android.synthetic.main.notes_list_fragment.view.*


class NotesListFragment : Fragment(R.layout.notes_list_fragment) {

    private var list: RecyclerView? = null
    private val adapter by lazy { NotesListAdapter() }


    companion object {
        fun newInstance() = NotesListFragment()
    }

//    private lateinit var viewModel: NotesListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = view.notes_list
        list?.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        list?.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
