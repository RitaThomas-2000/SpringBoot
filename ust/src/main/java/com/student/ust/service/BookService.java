package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book getBookByID(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
    public  void saveBook(Book book){
        book.setDate(LocalDateTime.now());
        book.setModifiedDate(book.getDate());
        bookRepository.save(book);
    }
    public void delBookByID(Integer id){
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book){
        Book updateBook=bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setAuthorName(book.getAuthorName());
        updateBook.setBookName(book.getBookName());
        updateBook.setModifiedDate(LocalDateTime.now());
        bookRepository.save(updateBook);
        return updateBook;

    }
}
