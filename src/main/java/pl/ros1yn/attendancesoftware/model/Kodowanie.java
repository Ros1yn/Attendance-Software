package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kodowanie")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Zajecia getZajecia() {
        return zajecia;
    }

    public void setZajecia(Zajecia zajecia) {
        this.zajecia = zajecia;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }
}
