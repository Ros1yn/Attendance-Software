package pl.ros1yn.attendancesoftware.lesson.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
