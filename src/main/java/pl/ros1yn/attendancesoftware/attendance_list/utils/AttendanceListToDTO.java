package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTO;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceToDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListToDTO {

    private final AttendanceToDTO attendanceToDTO;

    public AttendanceListDTO convertToDTO(AttendanceList attendanceList){

        List<AttendanceDTO> attendanceDTOList = attendanceList.getAttendanceList().stream()
                .map(attendanceToDTO::convert)
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
                attendanceDTOList
        );
    }

}
