package practise.bookstore.generics.service;

import practise.bookstore.generics.exceptions.NotFoundException;
import practise.models.BaseEntity;

/**
 * The interface for the Generic service.
 *
 * @param <T> the model ({@link practise.models.author.Author Author}, {@link practise.models.Category Category}, {@link practise.models.Book Book}, etc.)
 */
public interface GenericService <T extends BaseEntity> {

    /**
     * Finds all models from the database.
     *
     * @return the iterable of models
     */
    Iterable <T> findAll();

    /**
     * Find Model based on its ID.
     *
     * @param id the model`s ID
     * @return the model object from optional
     * @throws NotFoundException If the model was not found by the given identifier
     */
    T findById(Integer id) throws NotFoundException;

    /**
     * Saves a model to the database.
     *
     * @param t the model to save
     * @return the persisted model
     * @throws practise.bookstore.generics.exceptions.InvalidRequestMethodException if the POST method is used instead of PUT to save the model
     */
    T save(T t);

    /**
     * Updates a model.
     *
     * @param id the model's ID
     * @param t  the updated model
     * @return the updated model
     */
    T update(Integer id, T t);

    /**
     * Deletes a model by its ID from the database.
     *
     * @param id the model's ID
     */
    void deleteById(Integer id);

}


