package pl.ros1yn.attendancesoftware.attendance_list.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

public interface AttendanceListRepository extends CrudRepository<AttendanceList, Integer> {
    void deleteByLesson(Lesson lesson);
}
