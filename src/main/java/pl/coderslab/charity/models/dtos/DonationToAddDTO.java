package pl.coderslab.charity.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DonationToAddDTO {

    @Min(1)
    private int quantity;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotBlank
    private LocalTime pickUpTime;

    private String pickUpComments;

    @NotEmpty
    private List<Long> categoriesId;

    @NotNull
    private Long institutionId;
}
