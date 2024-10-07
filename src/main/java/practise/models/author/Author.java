package practise.models.author;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import practise.models.BaseEntity;
import practise.models.Book;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

}
