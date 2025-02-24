package pl.ros1yn.attendancesoftware.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {
}
