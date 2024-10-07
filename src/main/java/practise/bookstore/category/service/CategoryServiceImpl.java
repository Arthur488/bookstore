package practise.bookstore.category.service;

import org.springframework.stereotype.Service;
import practise.bookstore.category.dao.CategoryRepository;
import practise.bookstore.generics.service.GenericServiceImpl;
import practise.models.Category;

@Service
public class CategoryServiceImpl extends GenericServiceImpl <Category> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }

}
