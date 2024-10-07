package practise.bookstore.report.dao;

import practise.bookstore.generics.dao.GenericRepository;
import practise.models.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends GenericRepository<Order> {
    List<Order> findByCreatedDateBetween(LocalDateTime createdDate, LocalDateTime createdDate2);

}
