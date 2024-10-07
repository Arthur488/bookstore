package practise.bookstore.order.controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.order.service.OrderService;
import practise.models.order.Order;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void findOrdersByAuthorsName() throws Exception {

        //Что бы не перегружать код, предположим, что у модели order имеются все нужные поля и у поля author firstName: Lev
        Order order = new Order();

        when(orderService.findOrdersByBooksAuthorName("Lev")).thenReturn(List.of(order));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/orders/keyword/Lev");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "{\"model\":[{\"id\":null,\"createdBy\":null,\"createdDate\":null,\"updatedBy\":null,\"updatedDate\":null,\"totalPrice\":null,\"orderItems\":null}],\"message\":\"1 results found\"}";

        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    void findOrdersByAuthorsNameThrowsException() throws Exception {
        String keyword = "Lev123";
        when(orderService.findOrdersByBooksAuthorName(keyword)).thenThrow(new NotFoundException("No results for keyword " + keyword));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/orders/keyword/{keyword}", keyword);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(content().string("No results for keyword " + keyword))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
    }

}

