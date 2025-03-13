package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListMapper {

    private final AttendanceMapper attendanceMapper;
    private final LessonMapper lessonMapper;
    private final StudentRepository studentRepository;

    public AttendanceListResponse mapToResponseDTO(AttendanceList attendanceList) {

        List<AttendanceResponse> attendanceResponseList = attendanceList.getAttendances().stream()
                .map(attendanceMapper::mapToAttendanceResponse)
                .toList();

        return AttendanceListResponse.builder()
                .id(attendanceList.getId())
                .localDate(attendanceList.getDate())
                .lessonDTO(lessonMapper.mapToDTO(attendanceList.getLesson()))
                .attendanceResponseList(attendanceResponseList)
                .build();
    }

    public Attendance mapFromRequestDTOToAttendance(AttendanceListRequestDTO requestDTO, int i, List<Attendance> attendances) {
        Attendance attendance = attendances.get(i);
        AttendanceDTOForList attendanceResponse = requestDTO.getAttendances().get(i);

        Student student = studentRepository.findById(attendanceResponse.getIndexNumber())
                .orElseThrow(StudentNotFoundException::new);

        return Attendance.builder()
                .id(attendance.getId())
                .student(student)
                .isAttendance(attendanceResponse.getIsAttendance())
                .activity(0)
                .build();
    }

}
