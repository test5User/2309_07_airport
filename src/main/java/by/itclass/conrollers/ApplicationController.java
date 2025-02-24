package by.itclass.conrollers;

import by.itclass.model.repositories.AirplaneRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    private AirplaneRepository repository;

    @Autowired
    public void setRepository(AirplaneRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String root(HttpSession session) {
        session.setAttribute("ourPlanes", repository.findAll());
        return "airport";
    }
}
