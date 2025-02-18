package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

@Component
@AllArgsConstructor
public class AttendanceUpdate {

    private final AttendanceRepository attendanceRepository;

    public Attendance update(Attendance attendance, Attendance existingAttendance) {
        existingAttendance.setStudent(attendance.getStudent());
        existingAttendance.setIsAttendance(attendance.getIsAttendance());
        existingAttendance.setActivity(attendance.getActivity());
        existingAttendance.setAttendanceList(attendance.getAttendanceList());

        return attendanceRepository.save(existingAttendance);
    }

}
