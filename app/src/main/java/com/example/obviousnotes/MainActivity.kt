package com.example.obviousnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(bar)

        fab.setOnClickListener {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_notesListFragment_to_createNoteFragment)
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_check))
        }

    }
}
