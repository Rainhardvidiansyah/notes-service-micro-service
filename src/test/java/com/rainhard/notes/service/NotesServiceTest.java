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

import static org.junit.jupiter.api.Assertions.*;
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
        var notes = new Notes();
        List<Notes> listNotes = new ArrayList<>();
        listNotes.add(notes);

        when(notesRepository.getAllNotes()).thenReturn(listNotes);
        var service = notesService.getNotes();

        assertNotNull(service);
        assertEquals(service, listNotes);

    }
}