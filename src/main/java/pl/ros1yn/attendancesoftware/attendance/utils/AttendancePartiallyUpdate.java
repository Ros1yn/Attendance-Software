package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

@Component
@AllArgsConstructor
public class AttendancePartiallyUpdate {

    private final AttendanceRepository attendanceRepository;

    public Attendance update(Attendance attendance, Attendance existingAttendance) {
        if (attendance.getStudent() != null) existingAttendance.setStudent(attendance.getStudent());
        if (attendance.getIsAttendance() != null) existingAttendance.setIsAttendance(attendance.getIsAttendance());
        if (attendance.getActivity() != null) existingAttendance.setActivity(attendance.getActivity());
        if (attendance.getAttendanceList() != null)
            existingAttendance.setAttendanceList(attendance.getAttendanceList());

        return attendanceRepository.save(existingAttendance);
    }

}
