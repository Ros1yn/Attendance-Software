package pl.ros1yn.attendancesoftware.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "obecnosc")
@Getter
@Setter
@ToString
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

    //ilość + ("plusów") na zajęciach -> aktywnosc >= 0
    //przy put zmiana na ilość większą od 0
    @Column(name = "aktywnosc", nullable = false)
    private int aktywnosc;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lista_obecnosci_id", referencedColumnName = "id")
    private ListaObecnosci listaObecnosci;


}
