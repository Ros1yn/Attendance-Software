package pl.ros1yn.attendancesoftware.attendance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Entity
@Table(name = "obecnosc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_index_number", referencedColumnName = "index_number")
    private Student student;

    @Column(name = "czy_obecny", nullable = false)
    private Boolean isAttendance;

    @Column(name = "aktywnosc", nullable = false)
    private Integer activity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lista_obecnosci_id", referencedColumnName = "id")
    private AttendanceList attendanceList;
}
