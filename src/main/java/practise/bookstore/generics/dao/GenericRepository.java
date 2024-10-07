package practise.bookstore.generics.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import practise.models.BaseEntity;

public interface GenericRepository <T extends BaseEntity> extends CrudRepository<T, Integer> {


}
