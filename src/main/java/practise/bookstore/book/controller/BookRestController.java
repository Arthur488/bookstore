package practise.bookstore.book.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practise.bookstore.book.service.BookService;
import practise.bookstore.generics.controller.GenericRestController;
import practise.models.Book;

@RestController
@RequestMapping(value = "/api/books")
@Tag(name = "RESTful Web Services for Books", description = "Endpoints for managing books")
public class BookRestController extends GenericRestController <Book> {

    public BookRestController(BookService bookService) {
        super(bookService);
    }
}
