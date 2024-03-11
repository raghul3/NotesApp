package com.mobi.notesapp.presentation.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobi.notesapp.common.ScreenViewState
import com.mobi.notesapp.data.local.model.Note
import com.mobi.notesapp.presentation.home.NoteCard


@Composable
fun BookmarkScreen(
    state: BookmarkState,
    modifier: Modifier = Modifier,
    onBookMarkChange: (note: Note) -> Unit,
    onDelete: (Long) -> Unit,
    onNoteClicked: (Long) -> Unit,
) {
    when (state.notes) {
        is ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }

        is ScreenViewState.Success -> {
            val notes = state.notes.data
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                modifier = modifier,
            ) {
                itemsIndexed(notes) { index: Int, note: Note ->
                    NoteCard(
                        index = index,
                        note = note,
                        onBookmarkChange = onBookMarkChange,
                        onDeleteNote = onDelete,
                        onNoteClicked = onNoteClicked
                    )
                }
            }
        }

        is ScreenViewState.Error -> {
            Text(
                text = state.notes.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}
