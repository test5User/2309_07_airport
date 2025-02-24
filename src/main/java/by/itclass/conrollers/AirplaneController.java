package by.itclass.conrollers;

import by.itclass.model.entities.Airplane;
import by.itclass.model.repositories.AirplaneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {
    private AirplaneRepository repository;

    @Autowired
    public void setRepository(AirplaneRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAllPlanes(Model model) {
        model.addAttribute("planes", repository.findAll());
        return "airplanes";
    }

    @GetMapping("/newPlane")
    public String addNewPlane(@ModelAttribute("plane") Airplane plane) {
        return "new-plane";
    }

    @PostMapping
    public String saveNewPlane(@ModelAttribute("plane") @Valid Airplane plane,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "new-plane";
        }
        repository.save(plane);
        return "redirect:/airplanes";
    }

    @DeleteMapping("/{id}")
    public String deletePlane(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/airplanes";
    }
}
