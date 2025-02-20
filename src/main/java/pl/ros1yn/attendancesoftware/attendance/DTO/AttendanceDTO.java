package pl.ros1yn.attendancesoftware.attendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AttendanceDTO {

    public AttendanceDTO(StudentDTO studentDTO, Boolean isAttendance) {
        this.studentDTO = studentDTO;
        this.isAttendance = isAttendance;
    }

    private Integer id;
    private StudentDTO studentDTO;
    private Boolean isAttendance;

}
