package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lista_obecnosci")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Zajecia getZajecia() {
        return zajecia;
    }

    public void setZajecia(Zajecia zajecia) {
        this.zajecia = zajecia;
    }
}
