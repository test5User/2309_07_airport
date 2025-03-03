package by.itclass.model.repositories;

import by.itclass.model.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findByFlight_idAndPlace(int flightId, String place);
}
