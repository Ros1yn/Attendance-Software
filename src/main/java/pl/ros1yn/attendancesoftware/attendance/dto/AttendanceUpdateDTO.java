package pl.ros1yn.attendancesoftware.attendance.dto;

import lombok.Data;

@Data
public class AttendanceUpdateDTO {

    private Integer indexNumber;
    private String isAttendance;
    private Integer activity;
    private Integer listId;
}
