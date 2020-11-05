package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.models.entities.Donation;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "Select SUM(quantity) FROM donations", nativeQuery = true)
    int numberOfAllDonations();

    List<Donation> findDonationByUserId(long id);

    Donation findDonationById(long id);
}
