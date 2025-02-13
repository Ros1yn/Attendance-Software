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

    @JoinColumn(name = "student_id", referencedColumnName = "index_number", foreignKey = @ForeignKey(name = "obecnosc_ibfk_1"))
    @Column(nullable = false)
    private int studentId;

    @Column(name = "czyObecny", nullable = false)
    private Boolean czyObecny;

    //ilość + (plusów)

    @Column(name = "aktywnosc", nullable = false)
    private int aktywnosc;

}
