package guru.springframework.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}
