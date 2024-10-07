package practise.bookstore.author.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import practise.bookstore.author.service.AuthorService;
import practise.models.Book;
import practise.models.author.Author;
import practise.models.author.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

//The @WebMvcTest(AuthorRestController.class) annotation indicates that only the web service's AuthorRestController is being tested
@WebMvcTest(AuthorRestController.class)
class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * annotation that can be used to replace a bean with a mock object.
     * This is useful when you want to test a specific component or module in isolation from the rest of the application and need to control the behavior of its dependencies.
     */
    @MockBean
    private AuthorService authorService;

    @Test
    public void findAll() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("Lev");
        author.setLastName("Tolstoi");
        author.setDateOfBirth(LocalDate.of(1828, 9, 9));
        author.setGender(Gender.Male);

        when(authorService.findAll()).thenReturn(List.of(author));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/authors");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Временный вариант для примера будет с пустой строкой
        String expected = "[{}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findById() throws Exception {
        Author author = new Author();
        author.setId(1);
        author.setFirstName("Lev1");
        author.setLastName("Tolstoi");
        author.setDateOfBirth(LocalDate.of(1828, 9, 9));
        author.setGender(Gender.Male);

        Book book = new Book();
        book.setId(1);
        book.setName("War and Peace");
        book.setDescription("Desc");
        book.setCreatedBy("Arthur");
        book.setUpdatedBy("Arthur");
        book.setPrice(new BigDecimal(840));
        book.setSku("EL134FGSHFS1");

        author.setBooks(List.of(book));

        //Задаем поведение
        //Ищем по какому-то id (можно указать любой id, или any(), и находим именно такого автора
        when(authorService.findById(any())).thenReturn(author);

        //Отправка фейкового запроса. Проверяем следует ли поведение и результат указаному выше поведению и результату
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/authors/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Не используем конвертер для обьекта
        String expected = "{id: 1, firstName: \"Lev1\", lastName:\"Tolstoi\", dateOfBirth:  \"1828-09-09\", gender: \"Male\", books: [{" +
                "id: 1, createdBy: \"Arthur\", createdDate: null, updatedBy: \"Arthur\", updatedDate: null," +
                "name: \"War and Peace\", sku: \"EL134FGSHFS1\", description: \"Desc\", price: 840.00 }]}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testCreate() throws Exception {
        Author author = new Author();
        author.setId(100);
        author.setFirstName("Michael");
        author.setLastName("Tonkiy");
        author.setDateOfBirth(LocalDate.of(2001, 11, 1));
        author.setGender(Gender.Male);

        when(authorService.save(any(Author.class))).thenReturn(author);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Michael\",\"lastName\":\"Tonkiy\",\"dateOfBirth\":\"2001-11-01\",\"gender\":\"Male\", \"books\": null}");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"model\": {id: 100, firstName: \"Michael\", lastName:\"Tonkiy\", dateOfBirth: \"2001-11-01\", gender: \"Male\", books: null}, message: \"Model successfully saved!\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testUpdate() throws Exception {
        Author author = new Author();
        author.setId(100);
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setDateOfBirth(LocalDate.of(2012, 12, 2));
        author.setGender(Gender.Male);

        Integer id = 100;

        when(authorService.update(eq(id), any(Author.class))).thenReturn(author);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/authors/{id}", id)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":\"2012-12-02\",\"gender\":\"Male\"}");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{model: {id: 100, firstName: \"John\", lastName:\"Doe\", dateOfBirth: \"2012-12-02\", gender: \"Male\", books: null}, message: \"Model successfully updated!\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testDelete() throws Exception {

        Integer id = 100;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/authors/" + id);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(authorService).deleteById(id);
    }

}


























































