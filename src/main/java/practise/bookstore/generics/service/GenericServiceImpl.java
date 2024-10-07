package practise.bookstore.generics.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practise.bookstore.generics.dao.GenericRepository;
import practise.bookstore.generics.exceptions.InvalidRequestMethodException;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.models.AuditModel;
import practise.models.BaseEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class GenericServiceImpl <T extends BaseEntity> implements GenericService <T> {

    private final GenericRepository <T> genericRepository;

    @Override
    public Iterable <T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public T findById(Integer id) {
        return genericRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public T save(T t) {
        if (t.getId() != null) {
            throw new InvalidRequestMethodException();
        }
        return genericRepository.save(t);
    }

    @Override
    public T update(Integer id, T t) {
        T objectFromDB = findById(id);
        t.setId(id);
        if (t instanceof AuditModel tAudit && objectFromDB instanceof AuditModel dbAuditModel) {
            tAudit.setCreatedBy(dbAuditModel.getCreatedBy());
            tAudit.setCreatedDate(dbAuditModel.getCreatedDate());
        }
        return genericRepository.save(t);
    }

    @Override
    public void deleteById(Integer id) {
        if (!genericRepository.existsById(id)) {
            throw new NotFoundException();
        }
        genericRepository.deleteById(id);
    }

}
