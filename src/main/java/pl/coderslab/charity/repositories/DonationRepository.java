package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.models.entities.Donation;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "Select SUM(quantity) FROM Donation", nativeQuery = true)
    int numberOfAllDonations();
}
