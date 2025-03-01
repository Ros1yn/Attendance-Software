package pl.ros1yn.attendancesoftware.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonPropertyOrder({"id", "student", "isAttendance", "activity", "date"})
public class AttendanceResponse {

    private Integer id;
    @JsonProperty("student")
    private StudentDTO studentDTO;
    private Boolean isAttendance;
    private Integer activity;
    private LocalDate date;
}
