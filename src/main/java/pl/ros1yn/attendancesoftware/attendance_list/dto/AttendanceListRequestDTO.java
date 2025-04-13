package pl.ros1yn.attendancesoftware.attendance_list.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceDTOForList;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceListRequestDTO {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonProperty("lessonId")
    private Integer lessonId;

    @JsonProperty("attendance_list")
    private List<AttendanceDTOForList> attendances;

}
