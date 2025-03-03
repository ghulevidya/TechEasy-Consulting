package com.techeasy.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techeasy.library.Repository.BookRepo;
import com.techeasy.library.entity.Book;

@Service
public class BookServices {
	@Autowired
	private BookRepo bookRepo;

	public void add(Book book) {
		bookRepo.save(book);
		
	}
	public List<Book> getData() {
		return bookRepo.findAll();
		
	}
	public Optional<Book> getBookById(int id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id);
	}
	public Boolean updateBook(int id, Book book) {
		if(bookRepo.existsById(id)) {
			book.setBook_id(id);
			 bookRepo.save(book);
			 return true;
		}
		else
		return false  ;
	}
	public boolean deleteBook(int id) {
		if(bookRepo.existsById(id)) {
			bookRepo.deleteById(id);
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	

}
