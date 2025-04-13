package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonSimpleDTOMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceListDTOMapper {

    private final AttendanceItemMapper attendanceItemMapper;

    private final LessonSimpleDTOMapper lessonSimpleDTOMapperDTO;

    public AttendanceListPostDTO mapToListPostDTO(AttendanceList savedList, List<Attendance> attendances) {
        return new AttendanceListPostDTO(
                savedList.getId(),
                savedList.getDate(),
                lessonSimpleDTOMapperDTO.convertToSimpleDTO(savedList.getLesson()),
                attendances.stream()
                        .map(attendanceItemMapper::toPostDTO)
                        .toList());
    }

}
