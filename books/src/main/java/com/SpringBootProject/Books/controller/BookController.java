package com.SpringBootProject.Books.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import com.SpringBootProject.Books.request.BookRequest;
import com.SpringBootProject.Books.exception.BookNotFoundException;
import com.SpringBootProject.entity.Book;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book Management REST APIs")
@Validated
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        initializeBooks();
    }

    private void initializeBooks() {
        books.addAll(Arrays.asList(
                new Book(1L, "Clean Code", "Robert C. Martin", "Programming", 4.7),
                new Book(2L, "Effective Java", "Joshua Bloch", "Programming", 4.8),
                new Book(3L, "Atomic Habits", "James Clear", "Self Help", 4.6),
                new Book(4L, "Spring in Action", "Craig Walls", "Programming", 4.5),
                new Book(5L, "The Alchemist", "Paulo Coelho", "Fiction", 4.3)
        ));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books or filter by category")
    public List<Book> getBooks(
            @Parameter(description = "Filter books by category")
            @RequestParam(required = false) String category) {

        if (category == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get book by ID")
    public Book getBookById(
            @Parameter(description = "Book ID")
            @PathVariable
            @Positive(message = "Book ID must be positive")
            @Min(value = 1, message = "Book ID must be at least 1")
            long id) {

        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new BookNotFoundException("Book not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new book")
    public Book createBook(@Valid @RequestBody BookRequest bookRequest) {

        long id = books.isEmpty()
                ? 1
                : books.get(books.size() - 1).getId() + 1;

        Book book = convertToBook(id, bookRequest);
        books.add(book);

        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update existing book")
    public Book updateBook(
            @Parameter(description = "Book ID")
            @PathVariable
            @Positive(message = "Book ID must be positive")
            @Min(value = 1, message = "Book ID must be at least 1")
            long id,
            @Valid @RequestBody BookRequest bookRequest) {

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book updatedBook = convertToBook(id, bookRequest);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }

        throw new BookNotFoundException("Book not found with id: " + id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book")
    public void deleteBook(
            @Parameter(description = "Book ID")
            @PathVariable
            @Positive(message = "Book ID must be positive")
            @Min(value = 1, message = "Book ID must be at least 1")
            long id) {

        boolean removed = books.removeIf(book -> book.getId() == id);

        if (!removed) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    private Book convertToBook(long id, BookRequest bookRequest) {
        return new Book(
                id,
                bookRequest.getTitle(),
                bookRequest.getAuthor(),
                bookRequest.getCategory(),
                bookRequest.getRating()
        );
    }

    
}
