package com.emmariescurrena.bookesy.book_service.models;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("books_authors")
public class BookAuthor {
    
    @Column("book_id")
    @NotNull(message = "The bookId is required")
    private String bookId;

    @Column("author_id")
    @NotNull(message = "The authorId is required")
    private String authorId;


}
