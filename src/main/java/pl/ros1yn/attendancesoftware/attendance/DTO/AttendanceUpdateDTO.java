package pl.ros1yn.attendancesoftware.attendance.DTO;

import lombok.Data;

@Data
public class AttendanceUpdateDTO {

    private Integer indexNumber;

    private Boolean isAttendance;

    private Integer activity;

}
