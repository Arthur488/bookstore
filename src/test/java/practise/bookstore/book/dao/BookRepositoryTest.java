package practise.bookstore.book.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import practise.models.Book;
import practise.models.Category;
import practise.models.author.Author;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setName("War and Peace");
        book.setSku("EL134FGSHFS");
        book.setDescription("Book about 18-th century times and its people living their lives.");
        book.setPrice(new BigDecimal(840));

        Author author = entityManager.find(Author.class, 1);
        Category category = entityManager.find(Category.class, 1);
        book.setAuthor(author);
        book.setCategory(category);

        Book savedBook = bookRepository.save(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    void testFindBookByID() {
        Optional <Book> optional = bookRepository.findById(1);
        assertThat(optional).isPresent();
    }

    @Test
    void testFindBooksByCategory() {
        Category category = entityManager.find(Category.class, 1);
        List <Book> books = category.getBooks();
        books.forEach(System.out::println);
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksByAuthor() {
        Author author = entityManager.find(Author.class, 1);
        List <Book> books = author.getBooks();
        books.forEach(System.out::println);
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindAllBooks() {
        Iterable <Book> all = bookRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void testUpdateBookByID() {
        Integer book_id = 1;
        Optional <Book> byId = bookRepository.findById(book_id);
        assertThat(byId).isPresent();
        Book book = byId.get();
        book.setPrice(new BigDecimal(1280));
        Book bookUpdated = bookRepository.findById(book_id).get();
        assertThat(bookUpdated.getPrice()).isEqualTo(new BigDecimal(1280));
    }

    @Test
    void testDeleteBookByID() {
        Integer book_id = 1;
        bookRepository.deleteById(book_id);
        Optional <Book> byId = bookRepository.findById(book_id);
        assertThat(byId).isNotPresent();
    }

}