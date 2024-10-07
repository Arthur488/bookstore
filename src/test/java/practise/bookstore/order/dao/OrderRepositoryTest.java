package practise.bookstore.order.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import practise.models.Book;
import practise.models.order.Order;
import practise.models.order.OrderItem;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateOrder() {

        Order order = new Order();

        OrderItem orderItem1 = new OrderItem();
        Book book1 = entityManager.find(Book.class, 1);
        orderItem1.setBook(book1);
        orderItem1.setQuantity(7);
        orderItem1.setPricePaid(book1.getPrice());
        orderItem1.setOrder(order);

//        OrderItem orderItem2 = new OrderItem();
//        Book book2 = entityManager.find(Book.class, 2);
//        orderItem2.setBook(book2);
//        orderItem2.setQuantity(5);
//        orderItem2.setPricePaid(book2.getPrice());
//        orderItem2.setOrder(order);

//        order.setOrderItems(List.of(orderItem1, orderItem2));
        order.setOrderItems(List.of(orderItem1));

        orderRepository.save(order);

        assertThat(order.getId()).isNotNull();
    }

    @Test
    void testFindOrderByID() {
        Optional <Order> optional = orderRepository.findById(2);
        assertThat(optional).isPresent();
    }

//    @Test
//    void testUpdateOrder() {
//        Integer id = 2;
//        Optional <Order> optional = orderRepository.findById(id);
//        assertThat(optional).isPresent();
//        Order order = optional.get();
//        order.setUpdatedBy("Viktor");
//        orderRepository.save(order);
//        Order updatedOrder = orderRepository.findById(id).get();
//        assertThat("Viktor").isEqualTo(updatedOrder.getUpdatedBy());
//    }

    @Test
    void testDeleteOrderByID() {
        Integer order_id = 2;
        orderRepository.deleteById(order_id);
        Optional <Order> byId = orderRepository.findById(order_id);
        assertThat(byId).isNotPresent();
    }

    @Test
    void testFindOrderByBookAuthorName(){

        String authorName = "Ta";

        List <Order> ordersByBookAuthorsName = orderRepository.findOrdersByBookAuthorsName(authorName);
        ordersByBookAuthorsName.forEach(order -> System.out.println(order.getId()));


    }

}