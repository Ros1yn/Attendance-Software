package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListMapper {

    private final AttendanceMapper attendanceMapper;

    public AttendanceListDTO convertToDTO(AttendanceList attendanceList) {

        List<AttendanceResponse> attendanceResponseList = attendanceList.getAttendanceList().stream()
                .map(attendanceMapper::mapToAttendanceResponse)
                .toList();

        LessonSimpleDTO lessonSimpleDTO = new LessonSimpleDTO(
                attendanceList.getLesson().getId(),
                attendanceList.getLesson().getTitle(),
                attendanceList.getLesson().getSemester(),
                attendanceList.getLesson().getYear()
        );

        return new AttendanceListDTO(
                attendanceList.getId(),
                attendanceList.getDate(),
                lessonSimpleDTO,
                attendanceResponseList
        );
    }


    public void transfer(Iterable<AttendanceList> all, List<AttendanceListDTO> attendanceListDTOList) {
        for (AttendanceList attendanceListDTO : all) {
            attendanceListDTOList.add(convertToDTO(attendanceListDTO));
        }
    }

}
