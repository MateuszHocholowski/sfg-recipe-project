package guru.springframework.recipeproject.domain;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;
}
