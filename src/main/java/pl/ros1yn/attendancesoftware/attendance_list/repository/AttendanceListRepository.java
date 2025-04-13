package pl.ros1yn.attendancesoftware.attendance_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

public interface AttendanceListRepository extends JpaRepository<AttendanceList, Integer> {
    void deleteByLesson(Lesson lesson);
}
