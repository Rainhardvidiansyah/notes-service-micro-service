package com.rainhard.notes.service;

import com.rainhard.notes.entity.Notes;
import com.rainhard.notes.repository.NotesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotesServiceTest {

    @InjectMocks
    private NotesService notesService;

    @Mock
    private NotesRepository notesRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getNotes() {
        var note = new Notes();
        note.setTitle("Title");
        List<Notes> listNotes = new ArrayList<>();
        listNotes.add(note);

        when(notesRepository.getAllNotes()).thenReturn(listNotes);
        List<Notes> notes = notesService.getNotes();

        assertNotNull(note);
        assertNotNull(notes.get(0).getCreatedAt());
        assertEquals(1, notes.size());
        assertEquals("Title", notes.get(0).getTitle());

        verify(notesRepository).getAllNotes();

    }

    @Test
    void findNoteById() {
        var note = new Notes();
        note.setId(1L);
        note.setTitle("Title");
        note.setContent("Content");

        when(notesRepository.findOneNote(1L)).thenReturn(Optional.of(note));

        var service = notesService.findNoteById(1L);
        assertNotNull(service);
        assertEquals(1L, note.getId());
        assertEquals("Title", note.getTitle());

        assertNotNull(note.getCreatedAt());
        assertNotNull(note.getUpdatedAt());

        verify(notesRepository).findOneNote(1L); //specific argument
        verify(notesRepository).findOneNote(anyLong()); // does not care about specific arguments, then use "any"
    }

    @Test
    void getTitle() {
        var note = new Notes();
        note.setId(1L);
        note.setTitle("Title");
        note.setContent("Content");


        when(notesRepository.getNotesFromTitle("Title")).thenReturn(List.of(note));
        var service = notesService.getTitle("Title");

        assertNotNull(service);
        assertEquals("Title", service.get(0).getTitle());
        assertEquals(1, service.size());

        verify(notesRepository).getNotesFromTitle(any(String.class));
    }

    @Test
    void updateTitleAndContent() {

        when(notesRepository.updateTitleAndContent(anyLong(), anyString(), anyString()))
                .thenReturn(1);

        int service = notesService.updateTitleAndContent(1L, "Title", "Content");

        verify(notesRepository).updateTitleAndContent(anyLong(), anyString(), anyString());

        assertEquals(1, service);
    }
}