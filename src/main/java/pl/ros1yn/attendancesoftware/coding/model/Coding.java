package pl.ros1yn.attendancesoftware.coding.model;

import jakarta.persistence.*;
import lombok.*;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Entity
@Table(name = "kodowanie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Coding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "index_number", referencedColumnName = "index_number")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_zajec", referencedColumnName = "id")
    private Lesson lesson;

    @Column(name = "grupa", nullable = false)
    private Integer group;

}
