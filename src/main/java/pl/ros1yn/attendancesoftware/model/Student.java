package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Table(name = "student")
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "index_number", nullable = false)
    private Integer indexNumber;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    @Column(name = "imie", nullable = false)
    private String imie;


}
