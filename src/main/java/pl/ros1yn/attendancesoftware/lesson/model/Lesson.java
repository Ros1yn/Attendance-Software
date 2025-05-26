package pl.ros1yn.attendancesoftware.lesson.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import pl.ros1yn.attendancesoftware.coding.model.Coding;

import java.util.List;

@Entity
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "year", nullable = false)
    private Integer year;

    @JsonManagedReference("lesson-coding")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coding> coding;
}
