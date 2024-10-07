package practise.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The BaseEntity class serves as the base class for all entities with PK id in the system.
 * It provides a common set of properties and behavior for entity classes.
 */
@Data
@MappedSuperclass
public class BaseEntity {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * Default constructor for BaseEntity.
     */
    public BaseEntity() {
    }

    /**
     * Constructor for BaseEntity with the specified ID.
     *
     * @param id the identifier for the entity
     */
    public BaseEntity(Integer id) {
        this.id = id;
    }

}
