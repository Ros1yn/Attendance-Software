package pl.ros1yn.attendancesoftware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    public Boolean getCzyObecny() {
        return czyObecny;
    }

    public void setCzyObecny(Boolean czyObecny) {
        this.czyObecny = czyObecny;
    }

    public int getAktywnosc() {
        return aktywnosc;
    }

    public void setAktywnosc(int aktywnosc) {
        this.aktywnosc = aktywnosc;
    }
}
