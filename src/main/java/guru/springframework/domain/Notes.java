package guru.springframework.domain;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;
}
