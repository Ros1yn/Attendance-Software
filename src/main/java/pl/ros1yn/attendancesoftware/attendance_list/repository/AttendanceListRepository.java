package pl.ros1yn.attendancesoftware.attendance_list.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;

public interface AttendanceListRepository extends CrudRepository<AttendanceList, Integer> {
}
