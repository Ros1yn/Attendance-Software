package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceListNotFoundException;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;
import pl.ros1yn.attendancesoftware.utils.Finder;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class AttendanceListFinder implements Finder<AttendanceList, Integer> {

    private final AttendanceListRepository attendanceListRepository;

    @Override
    public AttendanceList find(Integer listId) {
        return attendanceListRepository.findById(listId)
                .orElseThrow(() -> {
                    log.error("Attendance list was not found");
                    return new AttendanceListNotFoundException();
                });
    }

}
