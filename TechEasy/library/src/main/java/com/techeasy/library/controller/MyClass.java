package com.techeasy.library.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techeasy.library.entity.Book;
import com.techeasy.library.service.BookServices;

@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "http://localhost:5173")

public class MyClass {

	@Autowired
	private BookServices service;
	 
	
	 @PostMapping("/putapi")
	    public ResponseEntity<String> putData(@RequestBody Book book) {
	        try {
	            service.add(book);
	            return ResponseEntity.status(201).body("Book added successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error: " + e.getMessage());
	        }
	    }
	 @GetMapping("/getapi")
	 public ResponseEntity<List<Book>> getData() {
	     try {
	         List<Book> books = service.getData(); 
	         return ResponseEntity.ok(books); 
	     } catch (Exception e) {
	         return ResponseEntity.status(500).body(null); 
	     }
	 }
	// Get book by id
	    @GetMapping("/books/{id}")
	    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
	        try {
	            Optional<Book> book = service.getBookById(id);
	            return book.map(ResponseEntity::ok)
	                       .orElseGet(() -> ResponseEntity.status(404).body(null));
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body(null);
	        }
	    }

	    // Update a book (Replace entire book details)
	    @PutMapping("/books/{id}")
	    public ResponseEntity<String> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
	        try {
	            boolean isUpdated = service.updateBook(id, book);
	            if (isUpdated) {
	                return ResponseEntity.status(200).body("Book updated successfully");
	            } else {
	                return ResponseEntity.status(404).body("Book not found");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error: " + e.getMessage());
	        }
	    }

	 // Delete a book
	    @DeleteMapping("/books/{id}")
	    public ResponseEntity<String> deleteBook(@PathVariable("id") int id) {
	        try {
	            boolean isDeleted = service.deleteBook(id);
	            if (isDeleted) {
	                return ResponseEntity.status(200).body("Book deleted successfully");
	            } else {
	                return ResponseEntity.status(404).body("Book not found");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error: " + e.getMessage());
	        }
	    } 
	 }
    


