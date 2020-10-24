package pl.coderslab.charity.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity @Getter @Setter
@Table(name = Donation.TABLE)
public class Donation {
    public final static String TABLE = "donations";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private String street;
    private String city;
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;

    @OneToMany
    private List<Category> categories;

    @OneToOne
    private Institution institution;
}
