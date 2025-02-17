package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kodowanie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Kodowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "index_number", referencedColumnName = "index_number")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_zajec", referencedColumnName = "id")
    private Zajecia zajecia;

    @Column(name = "grupa", nullable = false)
    private int grupa;

}
