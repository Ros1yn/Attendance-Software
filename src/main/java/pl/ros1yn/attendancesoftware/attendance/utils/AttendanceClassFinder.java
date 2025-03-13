package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceListNotFoundException;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class AttendanceClassFinder {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final AttendanceListRepository attendanceListRepository;

    public Attendance findAttendance(Integer id) {
        return attendanceRepository.findById(id)
                .orElseThrow(AttendanceNotFoundException::new);
    }

    public Student findStudent(AttendanceUpdateDTO updateDTO) {
        return studentRepository.findById(updateDTO.getIndexNumber())
                .orElseThrow(StudentNotFoundException::new);
    }

    public AttendanceList findAttendanceList(AttendanceUpdateDTO updateDTO) {
        return attendanceListRepository.findById(updateDTO.getListId())
                .orElseThrow(AttendanceListNotFoundException::new);
    }
}
