package practise.bookstore.category.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import practise.models.Category;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateCategory(){
        Category category = new Category();
        category.setName("Novel");
//        Book book = entityManager.find(Book.class, 1);
//        category.setBooks(List.of(book));
        Category saveCategory = categoryRepository.save(category);
        assertThat(saveCategory.getId()).isNotNull();
    }

    @Test
    void testFindCategoryByID() {
        Optional <Category> optional = categoryRepository.findById(1);
        assertThat(optional).isPresent();
    }

    @Test
    @Rollback
    void testUpdateCategoryByID() {
        Optional <Category> optional = categoryRepository.findById(1);
        assertThat(optional).isPresent();
        Category category = optional.get();
        category.setName("New category name");
        assertThat(category.getName()).isEqualTo("New category name");
    }

    @Test
    void testDeleteCategoryByID() {
        Integer category_id = 1;
        categoryRepository.deleteById(category_id);
        Optional <Category> byId = categoryRepository.findById(category_id);
        assertThat(byId).isNotPresent();
    }

}