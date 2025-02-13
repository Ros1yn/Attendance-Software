package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "obecnosc")
public class Obecnosc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "index_number")
    private Student student;

    @Column(name = "czy_obecny", nullable = false)
    private Boolean czyObecny;

    //ilość + (plusów na zajęciach)
    @Column(name = "aktywnosc", nullable = false)
    private int aktywnosc;

}
