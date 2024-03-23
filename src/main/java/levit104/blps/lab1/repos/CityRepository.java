package levit104.blps.lab1.repos;

import levit104.blps.lab1.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameAndCountry_Name(String name, String countryName);
}
