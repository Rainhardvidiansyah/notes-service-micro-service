package com.rainhard.notes.repository;

import com.rainhard.notes.entity.Notes;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {

    @Query("SELECT * FROM notes")
    List<Notes> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id")
    Optional<Notes> findOneNote(@Param("id") Long id);

    @Query("SELECT * FROM notes  WHERE title = :title")
    List<Notes> getNotesFromTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE notes SET title = :title, content= :content WHERE id = :id")
    int updateTitleAndContent(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
}
