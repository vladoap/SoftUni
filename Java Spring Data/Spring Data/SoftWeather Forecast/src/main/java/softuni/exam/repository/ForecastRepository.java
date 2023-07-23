package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DayOfWeek;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Optional<Forecast> findAllByCityAndDayOfWeek(City city, DayOfWeek dayOfWeek);

    Optional<Object> findByCityIdAndDayOfWeek(Long city, DayOfWeek dayOfWeek);

    @Query("SELECT f FROM Forecast f JOIN FETCH f.city c WHERE f.dayOfWeek = :dayOfWeek AND c.population < :population " +
            "ORDER BY f.maxTemp DESC, f.id")
    List<Forecast> findAllByDayOfWeekAndCityPopulationLessThan(DayOfWeek dayOfWeek, Integer population);
}
