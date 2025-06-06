package pl.ros1yn.attendancesoftware.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "student")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {

    @Id
    @Column(name = "index_number", nullable = false)
    private Integer indexNumber;

    @Column(name = "nazwisko", nullable = false)
    private String surname;

    @Column(name = "imie", nullable = false)
    private String name;

}
