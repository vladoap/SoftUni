package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.enums.ApartmentType;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("SELECT o FROM Offer o JOIN o.apartment a WHERE a.type = 'three_rooms' ORDER BY a.area DESC , o.price")
    List<Offer> findAllByApartmentTypeThreeRoomsOrderByApartmentAreaAndPrice();

}
