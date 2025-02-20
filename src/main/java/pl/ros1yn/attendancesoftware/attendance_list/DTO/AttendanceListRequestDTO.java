package pl.ros1yn.attendancesoftware.attendance_list.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceListRequestDTO {

    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonProperty("lessonId")
    private Integer lessonId;

    @JsonProperty("attendance_list")
    private List<AttendanceDTOForList> attendances;

}
