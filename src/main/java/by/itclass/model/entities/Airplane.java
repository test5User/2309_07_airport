package by.itclass.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "airplane")
@Getter
@Setter
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Model should be not empty")
    @Size(min = 2, max = 15, message = "Model mame should be between 2 and 15 characters")
    private String model;
    @Min(value = 1, message = "Places should be greater than 0")
    private int places;
}
