package practise.bookstore.author.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practise.bookstore.author.service.AuthorService;
import practise.bookstore.generics.controller.GenericRestController;
import practise.models.author.Author;

@RestController
@RequestMapping(value = "/api/authors")
@Tag(name = "RESTful Web Services for Authors", description = "Endpoints for managing authors")
public class AuthorRestController extends GenericRestController <Author> {

    public AuthorRestController(AuthorService authorService) {
        super(authorService);
    }

}

