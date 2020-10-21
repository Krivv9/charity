package pl.coderslab.charity.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate pickUpDate;
    private LocalDateTime pickUpTime;
    private String pickUpComment;

    @OneToMany(mappedBy = "donation")
    private List<Category> categories;

    @OneToOne
    private Institution institution;
}
