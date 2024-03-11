package com.mobi.notesapp.data.repository

import com.mobi.notesapp.data.local.NoteDao
import com.mobi.notesapp.data.local.model.Note
import com.mobi.notesapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : Repository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun getNoteById(id: Long): Flow<Note> {
        return noteDao.getNoteById(id)
    }

    override suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun update(note: Note) {
        noteDao.update(note)
    }

    override suspend fun delete(id: Long) {
        noteDao.delete(id)
    }

    override fun getBookMarkedNotes(): Flow<List<Note>> {
        return noteDao.getBookmarkedNotes()
    }
}
