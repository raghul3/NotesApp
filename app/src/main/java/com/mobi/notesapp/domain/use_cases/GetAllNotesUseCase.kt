package com.mobi.notesapp.domain.use_cases

import com.mobi.notesapp.data.local.model.Note
import com.mobi.notesapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> =
        repository.getAllNotes()
}