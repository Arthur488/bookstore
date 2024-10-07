package practise.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import practise.models.author.Author;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "books")
public class Book extends AuditModel {

    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Category category;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
