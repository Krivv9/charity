package pl.coderslab.charity.models.entities;

import lombok.*;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor
@AllArgsConstructor @Builder
@Table(name = Institution.TABLE)
public class Institution {
    public final static String TABLE = "institutions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}
