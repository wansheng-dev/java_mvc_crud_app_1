package com.ch3.pt2.bookdemo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ch3.pt2.bookdemo.models.Book;
import com.ch3.pt2.bookdemo.services.BookService;

@RestController
public class BooksApi {
	
	// dependency injection
    private final BookService bookService;
    public BooksApi(BookService bookService){
        this.bookService = bookService;
    }
    
    // get api request for all books
    @RequestMapping("/api/books")
    public List<Book> index() {
        return bookService.allBooks();
    }
    
    // post api request for creating a book
    @RequestMapping(value="/api/books", method=RequestMethod.POST)
    public Book create(
    		@RequestParam(value="title") String title,
    		@RequestParam(value="description") String desc,
    		@RequestParam(value="language") String lang,
    		@RequestParam(value="pages") Integer numOfPages
    		) {
    	Book book = new Book(title, desc, lang, numOfPages);
    	return bookService.createBook(book);
    } 
    
    // get api request for retrieving a book
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }
    
    // put api request for updating a book
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
    public Book update(
    		@PathVariable("id") Long id,
    		@RequestParam(value="title") String title,
    		@RequestParam(value="description") String desc,
    		@RequestParam(value="language") String lang,
    		@RequestParam(value="numberOfPages") Integer numberOfPages
    		) {
    	Book book = bookService.findBook(id);
    	book.setTitle(title);
    	book.setDescription(desc);
    	book.setLanguage(lang);
    	book.setNumberOfPages(numberOfPages);
    	
    	bookService.updateBook(book);
        return book;
    }
    
    // delete api request for deleting a book
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	bookService.deleteById(id);
    }
    
}
