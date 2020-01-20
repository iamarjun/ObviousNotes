# ObviousNotes
Obvious Take Home Exercise: Note Taking App. Have taken inspiration and design cues from google keep.

## Technical Specs
* Min API level: 21
* Targeted API level: 29
* Gradle Version: 5.4.1
* Android Gradle Plugin Version: 3.5.3
* Kotlin version: 1.3.61

## About Code Base
* Entire code is written in kotlin.
* Followed the MVVM architecture.
* Followed the one Activity rule.
* Gone with bottom app bar for the basic navigation. 

## Code Hierarchy
* I have gone with package per feature, so for every feature resides in their own package along with their dependant classes.

### createnote
* CreateNoteFragment: Let's the user create a note.

### noteDetails
* NoteDetailsFragment: Static screen which shows the note that was created.

### model
This package contains the data class.

### noteList
* NoteListFragment: The main fragment which contains the list of notes in chronological order.
* NoteListAdapter: RecyclerView adapter for the list of notes.

### Classes in root directory
* MainActivity: Just used as a starting point.
* NotesViewModel: ViewModel class shared between all the 3 fragment which handle all the opreations, like creation, fetching etc.
* SpacesItemDecoration: Just a custom item decoration class for recyclerview for spaces.

## Libraries Used
* Navigation Component from Android JetPack
* Material Design from Google

## Screenshots
Screen which contains the notes created by the user.
<img src="https://github.com/iamarjun/ObviousNotes/blob/master/screenshots/Screenshot_20200120-090653.png" width="300" >
<img src="https://github.com/iamarjun/ObviousNotes/blob/master/screenshots/Screenshot_20200120-090851.png" width="300" >

Screen to create a note.
<img src="https://github.com/iamarjun/ObviousNotes/blob/master/screenshots/Screenshot_20200120-090728.png" width="300" >

Static screen to view the details of the note.
<img src="https://github.com/iamarjun/ObviousNotes/blob/master/screenshots/Screenshot_20200120-090736.png" width="300" >


