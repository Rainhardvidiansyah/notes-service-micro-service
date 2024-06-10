package com.rainhard.notes.service;

import com.rainhard.notes.entity.Notes;
import com.rainhard.notes.jdbc.NotesJdbcRepository;
import com.rainhard.notes.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    public static final Logger log = LoggerFactory.getLogger(NotesService.class);
    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesJdbcRepository notesJdbcRepository;


    public void addNotes(String title, String content){
        this.notesJdbcRepository.insertNote(title, content);
    }

    public List<Notes> getNotes() {
        return this.notesRepository.getAllNotes();
    }

    public List<Notes> getTitle(String title) {
        return this.notesRepository.getNotesFromTitle(title);
    }

    public Optional<Notes> findNoteById(Long id){
//        return this.notesRepository.findOneNote(id);
        return this.notesJdbcRepository.getNotesById(id);
    }

    public int updateTitleAndContent(Long id, String title, String content){
        return this.notesRepository.updateTitleAndContent(id, title, content);
    }

    public List<Notes> getAllNotesJdbc(){
        return notesJdbcRepository.getNote();
    }
}
