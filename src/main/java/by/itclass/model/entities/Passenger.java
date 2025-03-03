package by.itclass.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passenger")
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "FIO should not be empty")
    private String fio;
    @NotEmpty(message = "Place should not be empty")
    @Pattern(regexp = "^(?:\\d|1\\d|2[0-5])[A-F]$", message = "Not 1A - 25F")
    private String place;
    @Transient
    private int flightId;
    @ManyToOne
    private Flight flight;
}
