package pl.ros1yn.attendancesoftware.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lista_obecnosci")
@Getter
@Setter
public class ListaObecnosci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_zajec", referencedColumnName = "id")
    private Zajecia zajecia;

    @JsonManagedReference
    @OneToMany(mappedBy = "listaObecnosci", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obecnosc> obecnosci = new ArrayList<>();


}
