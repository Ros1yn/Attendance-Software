package pl.ros1yn.attendancesoftware.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @Column(name = "index_number", nullable = false)
    private Integer indexNumber;

    @Column(name = "nazwisko", nullable = false)
    private String surname;

    @Column(name = "imie", nullable = false)
    private String name;


}
