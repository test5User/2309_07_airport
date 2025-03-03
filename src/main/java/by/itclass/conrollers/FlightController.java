package by.itclass.conrollers;

import by.itclass.model.entities.Flight;
import by.itclass.model.repositories.AirplaneRepository;
import by.itclass.model.repositories.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String saveNewFlight(@ModelAttribute("flight") @Valid Flight flight,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-flight";
        }
        flight.setAirplane(airplaneRepository.getById(flight.getPlaneId()));
        flightRepository.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/{id}")
    public String viewFlight(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "flight-info";
    }

    @DeleteMapping("/{id}")
    public String deleteFlight(@PathVariable("id") int id) {
        flightRepository.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/edit/{id}")
    public String editFlights(@PathVariable("id") int id,
                              Model model) {
        var flight = flightRepository.findById(id).get();
        flight.setPlaneId(flight.getAirplane().getId());
        model.addAttribute("flight", flight);
        return "edit-flight";
    }

    @PatchMapping
    public String updateFlight(@ModelAttribute("flight") Flight flight) {
        flight.setAirplane(airplaneRepository.getById(flight.getPlaneId()));
        flightRepository.save(flight);
        return "redirect:/flights";
    }
}
