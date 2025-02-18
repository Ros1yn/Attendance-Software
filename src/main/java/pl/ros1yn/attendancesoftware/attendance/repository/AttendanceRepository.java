package pl.ros1yn.attendancesoftware.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {
}
