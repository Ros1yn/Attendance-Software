package pl.ros1yn.attendancesoftware.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceDTOForList {

    private Integer attendanceId;
    private Integer indexNumber;
    private Integer activity;
    private String isAttendance;

}
