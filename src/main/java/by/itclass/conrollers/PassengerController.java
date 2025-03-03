package by.itclass.conrollers;

import by.itclass.model.entities.Passenger;
import by.itclass.model.repositories.FlightRepository;
import by.itclass.model.repositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;

    @Autowired
    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/add/{id}")
    public String addPassenger(@ModelAttribute("pass")Passenger passenger,
                               @PathVariable("id") int id) {
        passenger.setFlightId(id);
        return "add-pass";
    }

    @PostMapping
    public String savePass(@ModelAttribute("pass") @Valid Passenger passenger,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors() || passengerRepository.findByFlight_idAndPlace(passenger.getFlightId(), passenger.getPlace()) != null) {
            bindingResult.rejectValue("place", null,"Busy");
            return "add-pass";
        }
        passenger.setFlight(flightRepository.getById(passenger.getFlightId()));
        passengerRepository.save(passenger);
        return "redirect:/flights/" + passenger.getFlightId();
    }
}
