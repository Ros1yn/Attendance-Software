package pl.ros1yn.attendancesoftware.coding.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
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
    @JsonBackReference("lesson-coding")
    private Lesson lesson;

    @Column(name = "grupa", nullable = false)
    private Integer group;

}
