package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonDTO;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListMapper {

    private final AttendanceMapper attendanceMapper;

    public AttendanceListResponse mapToResponseDTO(AttendanceList attendanceList) {

        List<AttendanceResponse> attendanceResponseList = attendanceList.getAttendanceList().stream()
                .map(attendanceMapper::mapToAttendanceResponse)
                .toList();

        return AttendanceListResponse.builder()
                .id(attendanceList.getId())
                .localDate(attendanceList.getDate())
                .lessonDTO(
                        LessonDTO.builder()
                                .id(attendanceList.getLesson().getId())
                                .title(attendanceList.getLesson().getTitle())
                                .semester(attendanceList.getLesson().getSemester())
                                .year(attendanceList.getLesson().getYear())
                                .build())
                .attendanceResponseList(attendanceResponseList)
                .build();
    }
}
