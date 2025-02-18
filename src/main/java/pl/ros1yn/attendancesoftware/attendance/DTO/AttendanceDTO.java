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

    private Integer id;
    private StudentDTO studentDTO;
    private Boolean isAttendance;

}
