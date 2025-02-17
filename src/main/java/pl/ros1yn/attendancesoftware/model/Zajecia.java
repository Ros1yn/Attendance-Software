package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zajecia")
@Getter
@Setter
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

}
