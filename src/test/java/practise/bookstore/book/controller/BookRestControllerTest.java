package practise.bookstore.book.controller;

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
import practise.bookstore.book.service.BookService;
import practise.models.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void findAll() throws Exception {
        Book book = new Book();

        when(bookService.findAll()).thenReturn(List.of(book));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Временный вариант для примера будет с пустой строкой
        String expected = "[{}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void findById() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setName("War and Peace");
        book.setDescription("Desc");
        book.setCreatedBy("Arthur");
        book.setUpdatedBy("Arthur");
        book.setPrice(new BigDecimal(840));
        book.setSku("EL134FGSHFS1");


        //Задаем поведение
        //Ищем по какому-то id (можно указать любой id, или any(), и находим именно такого автора
        when(bookService.findById(any())).thenReturn(book);

        //Отправка фейкового запроса. Проверяем следует ли поведение и результат указаному выше поведению и результату
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Не используем конвертер для обьекта
        String expected = "{id: 1, createdBy: \"Arthur\", createdDate: null, updatedBy: \"Arthur\", updatedDate: null," +
                "name: \"War and Peace\", sku: \"EL134FGSHFS1\", description: \"Desc\", price: 840.00 }";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testCreate() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setName("War and Peace");
        book.setDescription("Desc");
        book.setCreatedBy("Arthur");
        book.setUpdatedBy("Arthur");
        book.setPrice(new BigDecimal(840));
        book.setSku("EL134FGSHFS1");

        when(bookService.save(any(Book.class))).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/books")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"createdBy\": \"Arthur\", \"createdDate\": null, \"updatedBy\": \"Arthur\", \"updatedDate\": null," +
                        "\"name\": \"War and Peace\", \"sku\": \"EL134FGSHFS1\", \"description\": \"Desc\", \"price\": 840.00 }");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{model: {id: 1, createdBy: \"Arthur\", createdDate: null, updatedBy: \"Arthur\", updatedDate: null," +
                "                       name: \"War and Peace\", sku: \"EL134FGSHFS1\", description: \"Desc\", price: 840.00 }, message: \"Model successfully saved!\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testUpdate() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setName("War and Peace 999");
        book.setDescription("Desc");
        book.setCreatedBy("Arthur");
        book.setUpdatedBy("Arthur");
        book.setPrice(new BigDecimal(840));
        book.setSku("EL134FGSHFS1");

        Integer id = 100;

        when(bookService.update(eq(id), any(Book.class))).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/books/{id}", id)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"createdBy\": \"Arthur\", \"createdDate\": null, \"updatedBy\": \"Arthur\", \"updatedDate\": null," +
                        "\"name\": \"War and Peace 999\", \"sku\": \"EL134FGSHFS1\", \"description\": \"Desc\", \"price\": 840.00 }");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{model: {id: 1, createdBy: \"Arthur\", createdDate: null, updatedBy: \"Arthur\", updatedDate: null," +
                "                       name: \"War and Peace 999\", sku: \"EL134FGSHFS1\", description: \"Desc\", price: 840.00 }, message: \"Model successfully updated!\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void testDelete() throws Exception {

        Integer id = 100;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/books/{id}", id);
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bookService).deleteById(id);
    }

}