package com.example.obviousnotes

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    internal val fab: FloatingActionButton by lazy { (activity as MainActivity).fab }
    internal val bar: BottomAppBar by lazy { (activity as MainActivity).bar }

    internal val viewModel: NotesViewModel by activityViewModels()

}