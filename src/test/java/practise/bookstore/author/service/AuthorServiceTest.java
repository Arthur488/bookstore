package practise.bookstore.author.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.models.author.Author;
import practise.models.author.Gender;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testFindAuthor() {
        Integer id = 1;
        Author author = authorService.findById(id);

        assertDoesNotThrow(() -> authorService.findById(id));
        assertNotNull(author);
        assertNotNull(author.getId());
        assertEquals("Lev", author.getFirstName());
    }

    @Test
    public void testFindAuthorCausedNotFoundException() {
        Integer id = 101;
        assertThrowsExactly(NotFoundException.class, () -> authorService.findById(id));
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setDateOfBirth(LocalDate.now());
        author.setGender(Gender.Male);


        Author savedAuthor = authorService.save(author);

        assertNotNull(savedAuthor);
        assertNotNull(savedAuthor.getId());
        assertEquals("John", savedAuthor.getFirstName());
    }

    @Test
    public void testUpdateAuthor() {
        Integer id = 10;
        Author authorFromDB = authorService.findById(id);

        String newName = "John";
        authorFromDB.setFirstName(newName);
        String newLastName = "Doe";
        authorFromDB.setLastName(newLastName);

        Author authorFromDBUpdated = authorService.update(id, authorFromDB);

        assertEquals(authorFromDBUpdated.getFirstName(), newName);
        assertNotNull(authorFromDBUpdated.getLastName(), newLastName);
    }

    @Test
    public void testDeleteAuthor() {
        Integer id = 12;
        assertThrowsExactly(NotFoundException.class, () -> {
            authorService.deleteById(id);
            authorService.findById(id);
        });
    }

}