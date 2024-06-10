package com.rainhard.notes.jdbc;

import com.rainhard.notes.entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

@Repository
public class NotesJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class NotesRowMapper implements RowMapper<Notes> {

        @Override
        public Notes mapRow(ResultSet rs, int rowNum) throws SQLException {
            var notes = new Notes();
            notes.setId(rs.getLong("id"));
            notes.setTitle(rs.getString("title"));
            notes.setContent(rs.getString("content"));
            return notes;
        }
    }


    public void insertNote(String title, String content){
        String sql = "INSERT INTO notes (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, title, content);

    }

    public Optional<Notes> getNotesById(Long id){
        String sql = "SELECT * FROM notes WHERE id = ?";
        var note = jdbcTemplate.queryForObject(sql, new NotesRowMapper(), id);
        return Optional.ofNullable(note);
    }

    public List<Notes> getNote(){
        String sql = "SELECT * FROM notes";
        return jdbcTemplate.query(sql, new NotesRowMapper());
    }

}
