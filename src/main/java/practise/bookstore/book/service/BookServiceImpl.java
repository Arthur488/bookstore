package practise.bookstore.book.service;

import org.springframework.stereotype.Service;
import practise.bookstore.book.dao.BookRepository;
import practise.bookstore.generics.service.GenericServiceImpl;
import practise.models.Book;

@Service
public class BookServiceImpl extends GenericServiceImpl <Book> implements BookService {

    public BookServiceImpl(BookRepository bookRepository) {
        super(bookRepository);
    }

}
