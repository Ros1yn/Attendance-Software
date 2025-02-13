package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indexNumber;

    @Column(nullable = false)
    private String nazwisko;

    @Column(nullable = false)
    private String imie;

}
