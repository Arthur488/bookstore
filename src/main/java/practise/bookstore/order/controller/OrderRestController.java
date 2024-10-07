package practise.bookstore.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practise.bookstore.generics.Response;
import practise.bookstore.generics.controller.GenericRestController;
import practise.bookstore.order.service.OrderService;
import practise.models.order.Order;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "RESTful Web Services for Orders", description = "Endpoints for managing orders")
public class OrderRestController extends GenericRestController <Order> {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }

    // TODO: 17.06.2023 Write tests for success response and exception 
    @GetMapping(value = "/keyword/{keyword}")
    @Operation(summary = "Find orders by keyword", description = "Find orders from date to date corresponded to task criteria (Find by fragment of name of book author)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Model(s) Not Found", content = @Content(schema = @Schema(implementation = Response.class))),
    })
    public ResponseEntity <Response <List <Order>>> findOrdersByAuthorsName(@PathVariable String keyword) {
        List <Order> orders = orderService.findOrdersByBooksAuthorName(keyword);
        String successMessage = orders.size() + " results found";
        return ResponseEntity.ok(new Response <>(orders, successMessage));
    }

}
