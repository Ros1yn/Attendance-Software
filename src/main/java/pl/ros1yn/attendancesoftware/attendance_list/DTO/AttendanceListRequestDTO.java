package pl.ros1yn.attendancesoftware.attendance_list.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceListRequestDTO {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Date can't be null")
    private LocalDate date;

    @JsonProperty("lessonId")
    @NotNull(message = "LessonId can't be null")
    private Integer lessonId;

    @JsonProperty("attendance_list")
    @NotNull(message = "Attendance list can't be null")
    @NotEmpty(message = "Attendance list can't be empty")
    @Size(min = 1, message = "Size of the list must be at least 1")
    private List<AttendanceDTOForList> attendances;

}
