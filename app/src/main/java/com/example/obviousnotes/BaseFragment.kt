package com.example.obviousnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.obviousnotes.createNote.CreateNoteFragment
import com.example.obviousnotes.createNote.CreateNoteFragmentDirections
import com.example.obviousnotes.noteList.NotesListFragment
import com.example.obviousnotes.noteList.NotesListFragmentDirections
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    internal val fab: FloatingActionButton by lazy { (activity as MainActivity).fab }
    internal val bar: BottomAppBar by lazy { (activity as MainActivity).bar }

    internal lateinit var mGoogleSignInClient: GoogleSignInClient

    internal val viewModel: NotesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }


    fun logout(fragment: Fragment) {
        mGoogleSignInClient.signOut().addOnSuccessListener {
            val action =
                when (fragment) {
                    is CreateNoteFragment -> CreateNoteFragmentDirections.actionCreateNoteFragmentToLoginFragment()
                    is NotesListFragment -> NotesListFragmentDirections.actionNotesListFragmentToLoginFragment()
                    else -> throw IllegalStateException()
                }
            findNavController().navigate(action)
        }
    }

}