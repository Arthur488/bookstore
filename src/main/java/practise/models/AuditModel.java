package practise.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Represents an {@link AuditModel auditable model} with creation and update metadata.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class AuditModel extends BaseEntity {

    /**
     * The username of the user who created this model.
     */
    @Column(updatable = false)
    private String createdBy;

    /**
     * The timestamp when this model was created.
     */
    @Column(updatable = false)
    private LocalDateTime createdDate;

    /**
     * The username of the user who last updated this model.
     */
    private String updatedBy;

    /**
     * The timestamp when this model was last updated.
     */
    private LocalDateTime updatedDate;

    /**
     * The username of the system user.
     */
    @Transient
    private static final String SYSTEM_USER = "Arthur";

    /**
     * Sets the creation and update metadata before persisting the model.
     */
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        // Задаем значение "создано/обновлено пользователем" как системного пользователя
        createdBy = SYSTEM_USER;
        updatedBy = SYSTEM_USER;
    }

    /**
     * Updates the update metadata before updating the model.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
        // Задаем значение "обновлено пользователем" как системного пользователя
        updatedBy = SYSTEM_USER;
    }

}
