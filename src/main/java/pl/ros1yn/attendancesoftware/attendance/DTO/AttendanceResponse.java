package pl.ros1yn.attendancesoftware.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AttendanceResponse {

    private Integer id;
    @JsonProperty("student")
    private StudentDTO studentDTO;

    private Boolean isAttendance;

    private Integer activity;

}
