package pl.ros1yn.attendancesoftware.attendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceUpdateDTO {

    private Integer indexNumber;

    private Boolean isAttendance;

    private Integer activity;

}
