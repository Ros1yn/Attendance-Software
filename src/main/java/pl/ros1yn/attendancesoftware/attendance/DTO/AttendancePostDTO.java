package pl.ros1yn.attendancesoftware.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendancePostDTO {

    @JsonProperty("student")
    private StudentDTO studentDTO;
    private Boolean isAttendance;
}
