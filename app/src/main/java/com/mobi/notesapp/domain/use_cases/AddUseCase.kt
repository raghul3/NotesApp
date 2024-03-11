package com.mobi.notesapp.domain.use_cases

import com.mobi.notesapp.data.local.model.Note
import com.mobi.notesapp.domain.repository.Repository
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) = repository.insert(note)
}