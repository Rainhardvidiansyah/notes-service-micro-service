CREATE TABLE note_categories(
    notes_id BIGINT NOT NULL,
    categories_id BIGINT NOT NULL,
    PRIMARY KEY (notes_id, categories_id),

    CONSTRAINT fk_notes
        FOREIGN KEY (notes_id)
        REFERENCES notes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_categories
        FOREIGN KEY (categories_id)
        REFERENCES categories(id)
        ON DELETE CASCADE,

        created_at timestamp DEFAULT CURRENT_TIMESTAMP,
        updatedAt timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );


