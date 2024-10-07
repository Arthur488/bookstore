package practise.bookstore.order.service;

import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.generics.service.GenericService;
import practise.models.order.Order;

import java.util.List;

public interface OrderService extends GenericService <Order> {

    List <Order> findOrdersByBooksAuthorName(String keyword) throws NotFoundException;

}
