package com.techeasy.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techeasy.library.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

}
