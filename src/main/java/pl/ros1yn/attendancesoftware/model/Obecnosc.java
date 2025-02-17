package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "obecnosc")
@Getter
@Setter
public class Obecnosc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_index_number", referencedColumnName = "index_number")
    private Student student;

    @Column(name = "czy_obecny", nullable = false)
    private Boolean czyObecny;

    //ilość + (plusów na zajęciach)
    @Column(name = "aktywnosc", nullable = false)
    private int aktywnosc;

    @ManyToOne
    @JoinColumn(name = "lista_obecnosci_id", referencedColumnName = "id")
    private ListaObecnosci listaObecnosci;


}
