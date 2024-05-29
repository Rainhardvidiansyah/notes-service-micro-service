package com.rainhard.notes.controller;


import com.rainhard.notes.dto.NotesRequestDto;
import com.rainhard.notes.entity.Notes;
import com.rainhard.notes.service.NotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    public static final Logger log = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    private NotesService notesService;

    @PostMapping("/")
    public ResponseEntity<?> createNote(@RequestBody NotesRequestDto notesRequestDto){
        this.notesService.addNotes(notesRequestDto.getTitle(), notesRequestDto.getContent());
        return new ResponseEntity<>(responses("Catatan berhasil disimpan"),
                HttpStatusCode.valueOf(201));
    }

    @GetMapping("/param")
    public ResponseEntity<?> getAllNotes(@RequestParam("title") String title){
        var notes = notesService.getTitle(title);
        if(notes.isEmpty()){
            return new ResponseEntity<>(responses("Catatan tidak ditemukan"),
                    HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(notes, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/")
    public List<Notes> getNotes(){
        log.info("Get all notes in getNotes method Notes Controller");
        return this.notesService.getNotes();
    }

    private <T> T responses(T t){
        return t;
    }

}
