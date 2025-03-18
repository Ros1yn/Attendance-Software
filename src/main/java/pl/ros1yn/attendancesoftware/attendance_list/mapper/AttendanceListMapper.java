package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListMapper {

    private final AttendanceMapper attendanceMapper;
    private final LessonMapper lessonMapper;

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

}
