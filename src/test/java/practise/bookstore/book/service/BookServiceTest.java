package practise.bookstore.book.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.models.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testFindBook() {
        Integer id = 1;
        Book book = bookService.findById(id);

        assertDoesNotThrow(() -> bookService.findById(id));
        assertNotNull(book);
        assertNotNull(book.getId());
        assertEquals("War and Peace", book.getName());
    }

    @Test
    public void testFindBookCausedNotFoundException() {
        Integer id = 101;
        assertThrowsExactly(NotFoundException.class, () -> bookService.findById(id));
    }

    @Test
    @Transactional
    public void testCreateBook() {
        Book book = new Book();
        book.setName("Test book name");
        book.setDescription("description");

        Book savedBook = bookService.save(book);

        assertNotNull(savedBook);
        assertNotNull(savedBook.getId());
        assertEquals("Test book name", savedBook.getName());
    }

    @Test
    @Transactional
    public void testUpdateBook() {
        Integer id = 3;
        Book bookFromDB = bookService.findById(id);

        String newName = "TEST NEW";
        bookFromDB.setName(newName);
        String newDesc = "Doe";
        bookFromDB.setDescription(newDesc);

        Book bookFromDBUpdated = bookService.update(id, bookFromDB);

        assertEquals(bookFromDBUpdated.getName(), newName);
        assertNotNull(bookFromDBUpdated.getDescription(), newDesc);
    }

    @Test
    public void testDeleteBook() {
        Integer id = 5;
        assertThrowsExactly(NotFoundException.class, () -> {
            bookService.deleteById(id);
            bookService.findById(id);
        });
    }

}