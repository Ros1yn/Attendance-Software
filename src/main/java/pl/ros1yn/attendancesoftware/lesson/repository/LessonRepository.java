package pl.ros1yn.attendancesoftware.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
