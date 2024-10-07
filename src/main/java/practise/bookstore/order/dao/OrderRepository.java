package practise.bookstore.order.dao;

import org.springframework.data.jpa.repository.Query;
import practise.bookstore.generics.dao.GenericRepository;
import practise.models.order.Order;

import java.util.List;

public interface OrderRepository extends GenericRepository <Order> {

    /**
     * The method findOrdersByBookAuthorsName is used to retrieve orders by searching for a fragment of the book author's name. The keyword parameter represents the fragment of the book author's name to search for.
     * <p>
     * The LIKE keyword is used to perform a partial match on the book author's first name. By using the % character before and after the :keyword parameter (LIKE %:keyword%), the search is not limited to exact matches. It allows searching for fragments of the author's name. For example, if the keyword parameter is "Ta", the query will retrieve orders with authors whose names contain the fragment "Ta" (e.g., "Taras", "Tatyana", "Tamara", etc.).
     *<p>
     * Using LIKE %:keyword% provides more flexibility and allows for a broader search based on the fragment of the author's name, compared to using LIKE ?1, which would only search for exact matches.
     *
     * @param keyword it should be a full first name or a fragment of book`s author`s first name
     * @return List of orders that meet the criteria of the query
     */
    @Query("SELECT o FROM Order o JOIN o.orderItems i WHERE i.book.author.firstName LIKE %:keyword%")
    List <Order> findOrdersByBookAuthorsName(String keyword);

}
