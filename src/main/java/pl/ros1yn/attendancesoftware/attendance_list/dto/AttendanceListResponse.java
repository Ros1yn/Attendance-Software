package pl.ros1yn.attendancesoftware.attendance_list.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceListResponse {

    private Integer id;
    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate localDate;
    @JsonProperty("lesson")
    private LessonDTO lessonDTO;
    @JsonProperty("attendance_list")
    private List<AttendanceResponse> attendanceResponseList;

}
