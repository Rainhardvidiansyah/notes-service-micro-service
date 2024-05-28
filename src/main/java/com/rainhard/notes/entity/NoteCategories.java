package com.rainhard.notes.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("note_categories")
public class NoteCategories {

    @Column("notes_id")
    private long notesId;

    @Column("categories_id")
    private Long categoryId;

    @CreatedDate
    @Column("createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updatedAt")
    private LocalDateTime updatedAt;

    public NoteCategories(long notesId, Long categoryId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.notesId = notesId;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getNotesId() {
        return notesId;
    }

    public void setNotesId(long notesId) {
        this.notesId = notesId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
