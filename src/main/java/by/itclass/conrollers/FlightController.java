package by.itclass.conrollers;

import by.itclass.model.entities.Flight;
import by.itclass.model.repositories.AirplaneRepository;
import by.itclass.model.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private FlightRepository flightRepository;
    private AirplaneRepository airplaneRepository;

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setAirplaneRepository(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights";
    }

    @GetMapping("/newFlight")
    public String addNewFlight(@ModelAttribute("flight") Flight flight) {
        return "new-flight";
    }
}
