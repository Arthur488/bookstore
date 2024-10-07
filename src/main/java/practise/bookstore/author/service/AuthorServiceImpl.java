package practise.bookstore.author.service;

import org.springframework.stereotype.Service;
import practise.bookstore.author.dao.AuthorRepository;
import practise.bookstore.generics.service.GenericServiceImpl;
import practise.models.author.Author;

@Service
public class AuthorServiceImpl extends GenericServiceImpl <Author> implements AuthorService {

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        super(authorRepository);
    }

}
