package com.rainhard.notes.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotesJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertNote(String title, String content){
        String sql = "INSERT INTO notes (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, title, content);
    }

}
