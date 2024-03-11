package com.mobi.notesapp.domain.use_cases

import com.mobi.notesapp.domain.repository.Repository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id:Long) = repository.getNoteById(id)
}