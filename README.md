# ObviousNotes
Obvious Take Home Exercise: Note Taking App

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

## Screenshots
<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150610.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150622.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150417.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150501.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150537.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150546.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150559.jpg" width="300" >


<img src="https://github.com/iamarjun/Instagram/blob/master/screenshots/Screenshot_20180722-150551.jpg" width="300" >

