package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

@Component
@AllArgsConstructor
public class SaveAttendanceList {

    private final AttendanceListRepository attendanceListRepository;

    public AttendanceList saveList(AttendanceListRequestDTO requestDTO, Lesson lesson) {
        AttendanceList attendanceList = new AttendanceList();
        attendanceList.setDate(requestDTO.getDate());
        attendanceList.setLesson(lesson);

        return attendanceListRepository.save(attendanceList);
    }
}
