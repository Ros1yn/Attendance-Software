package pl.ros1yn.attendancesoftware.attendance.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceResponse {

    private Integer id;
    @JsonProperty("student")
    private StudentDTO studentDTO;
    private String isAttendance;
    private Integer activity;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
}
