package pl.ros1yn.attendancesoftware.lesson.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.coding.model.Coding;

import java.util.List;

@Entity
@Table(name = "zajecia")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nazwa", nullable = false)
    private String title;

    @Column(name = "semestr", nullable = false)
    private Integer semester;

    @Column(name = "rok", nullable = false)
    private Integer year;

    @JsonManagedReference("lesson-coding")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coding> coding;
}
