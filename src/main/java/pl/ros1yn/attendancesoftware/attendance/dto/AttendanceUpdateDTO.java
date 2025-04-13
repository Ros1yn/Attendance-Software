package pl.ros1yn.attendancesoftware.attendance.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceUpdateDTO {

    private Integer indexNumber;
    private String isAttendance;
    private Integer activity;
    private Integer listId;
}
