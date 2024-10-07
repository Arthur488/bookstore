package practise.bookstore.generics.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practise.bookstore.generics.Response;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.generics.service.GenericService;
import practise.models.BaseEntity;

/**
 * The Generic Rest Controller.
 *
 * <p>
 * Available Methods:
 * <ul>
 * <li> findAll(): ResponseEntity&lt;Iterable&lt;T&gt;&gt;</li>
 * Retrieves all models. Returns a ResponseEntity with the iterable of models.
 * <p>
 * <li> find(Integer id): Object</li>
 * Retrieves a model by its ID. Returns the model object. Throws NotFoundException if the model was not found by the given identifier.
 * <p>
 * <li> save(T model): ResponseEntity&lt;Response&lt;T&gt;&gt;</li>
 * Creates a new model. Takes the model to create as a parameter. Returns a ResponseEntity with the created model and success message.
 * <p>
 * <li> update(Integer id, T model): ResponseEntity&lt;Response&lt;T&gt;&gt;</li>
 * Updates a model by its ID. Takes the updated model as a parameter.
 * <p>
 * <li> delete(Integer id): void</li>Deletes a model by its ID. Success based on a return type. 200 - performed, 404 - not found
 * <p>
 * </ul>
 *
 * @param <T> The model type ({@link practise.models.author.Author Author}, {@link practise.models.Category Category}, {@link practise.models.Book Book}, etc.)
 */

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Book Store`s API Documentation",
                version = "1.0",
                description = "Right below you can explore all RESTful Web Services of Book Store web application",
                contact = @Contact(name = "Arthur Rakhmankulov", email = "dev.arthur.r@gmail.com")
        ),
        servers = {
                @Server(url = "http://localhost:8080/Store/", description = "RESTful Web Services")
        }
)
public class GenericRestController <T extends BaseEntity> {

    private final GenericService <T> genericService;

    /**
     * Constructs a new GenericRestController with the specified GenericService implementation.
     *
     * @param genericService the GenericService implementation
     */
    public GenericRestController(@Qualifier("genericServiceImpl") GenericService <T> genericService) {
        this.genericService = genericService;
    }

    /**
     * Retrieves all models.
     *
     * @return the ResponseEntity with the iterable of models
     */
    @GetMapping
    @Operation(summary = "Find all models", description = "Returns a list of all models from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "204", description = "No Content"),
    })
    public ResponseEntity <?> findAll() {
        Iterable <T> models = genericService.findAll();
        return models.iterator().hasNext() ? ResponseEntity.ok(models) : ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a model by its ID.
     *
     * @param id the model's ID
     * @return the model object
     * @throws NotFoundException if the model was not found by the given identifier
     */
    @GetMapping("/{id}")
    @Operation(summary = "Find the model corresponding to the provided id", description = "Returns a list of all models from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Model Not Found"),
    })
    public T find(@PathVariable Integer id) {
        return genericService.findById(id);
    }

    /**
     * Creates a new model.
     *
     * @param entity the model to create
     * @return the ResponseEntity with the created model and success message
     */
    @PostMapping
    @Operation(summary = "Create a new model", description = "Creates a new model by saving the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New model created", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "405", description = "Method not allowed"),
            @ApiResponse(responseCode = "415", description = "Unsupported Media Type")
    })
    public ResponseEntity <Response <T>> save(@RequestBody T entity) {
        T savedModel = genericService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response <>(savedModel, "Model successfully saved!"));
    }

    /**
     * Updates a model by its ID.
     *
     * @param id     the model's ID
     * @param entity the updated model
     * @return the ResponseEntity with the updated model and success message
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing model", description = "Updates an existing model by saving the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Model updated", content = @Content(schema = @Schema(implementation = Response.class))),
    })
    public ResponseEntity <Response <T>> update(@PathVariable Integer id, @RequestBody T entity) {
        T updatedModel = genericService.update(id, entity);
        return ResponseEntity.ok(new Response <>(updatedModel, "Model successfully updated!"));
    }

    /**
     * Deletes a model by its ID.
     *
     * @param id the model's ID
     */
    @DeleteMapping(value = "/{id}", produces = "text/plain")
    @Operation(summary = "Delete an existing model", description = "Delete an existing model from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If No Errors"),
            @ApiResponse(responseCode = "404", description = "Model Not Found")
    })
    public void delete(@PathVariable Integer id) {
        genericService.deleteById(id);
    }

}
