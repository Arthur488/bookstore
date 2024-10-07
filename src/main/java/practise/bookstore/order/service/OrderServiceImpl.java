package practise.bookstore.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.generics.service.GenericServiceImpl;
import practise.bookstore.order.dao.OrderRepository;
import practise.models.order.Order;

import java.util.List;

@Service
public class OrderServiceImpl extends GenericServiceImpl <Order> implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    public List <Order> findOrdersByBooksAuthorName(String keyword) throws NotFoundException {
        List <Order> ordersByBookAuthorsName = orderRepository.findOrdersByBookAuthorsName(keyword);
        if (ordersByBookAuthorsName.isEmpty()) {
            throw new NotFoundException("No results for keyword '" + keyword + "'");
        }
        return ordersByBookAuthorsName;
    }
}
