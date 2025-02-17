package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "zajecia")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Zajecia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nazwa", nullable = false)
    private String nazwa;

    @Column(name = "semestr", nullable = false)
    private int semestr;

    @Column(name = "rok", nullable = false)
    private int rok;

    @OneToMany(mappedBy = "zajecia")
    private List<Kodowanie> kodowanie;
}
