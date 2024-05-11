package levit104.blps.lab1.repos.main;

import levit104.blps.lab1.models.main.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    boolean existsByName(String name);

    Optional<Tour> findByIdAndGuide_Username(Long id, String guideUsername);

    List<Tour> findAllByGuide_Username(String guideUsername, Pageable pageable);

    List<Tour> findAllByCity_NameAndCity_Country_Name(String cityName, String countryName, Pageable pageable);
}
