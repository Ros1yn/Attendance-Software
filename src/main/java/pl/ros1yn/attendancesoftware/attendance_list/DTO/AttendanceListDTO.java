package pl.ros1yn.attendancesoftware.attendance_list.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceListDTO {

    private Integer id;
    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate localDate;
    @JsonProperty("lesson")
    private LessonSimpleDTO lessonSimpleDTO;
    @JsonProperty("attendance_list")
    private List<AttendanceResponse> attendanceResponses;

}
