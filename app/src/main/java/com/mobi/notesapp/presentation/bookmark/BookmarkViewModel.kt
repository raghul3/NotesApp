package com.mobi.notesapp.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobi.notesapp.common.ScreenViewState
import com.mobi.notesapp.data.local.model.Note
import com.mobi.notesapp.domain.use_cases.DeleteNoteUseCase
import com.mobi.notesapp.domain.use_cases.FilteredBookmarkNotes
import com.mobi.notesapp.domain.use_cases.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val filteredBookmarkNotes: FilteredBookmarkNotes,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {
    private val _state: MutableStateFlow<BookmarkState> = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        getBookMarkedNotes()
    }


    private fun getBookMarkedNotes() {
        filteredBookmarkNotes().onEach {
            _state.value = BookmarkState(
                notes = ScreenViewState.Success(it)
            )
        }
            .catch {
                _state.value = BookmarkState(notes = ScreenViewState.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    fun onBookmarkChange(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(
                note.copy(
                    isBookMarked = !note.isBookMarked
                )
            )
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }


}

data class BookmarkState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading,
)