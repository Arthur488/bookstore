package practise.bookstore.category.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practise.bookstore.category.service.CategoryService;
import practise.bookstore.generics.controller.GenericRestController;
import practise.models.Category;

@RestController
@RequestMapping(value = "/api/categories")
@Tag(name = "RESTful Web Services for Categories", description = "Endpoints for managing categories")
public class CategoryRestController extends GenericRestController <Category> {

    public CategoryRestController(CategoryService categoryService) {
        super(categoryService);
    }


}
