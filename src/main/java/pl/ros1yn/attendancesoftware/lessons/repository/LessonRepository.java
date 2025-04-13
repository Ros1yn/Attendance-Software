package pl.ros1yn.attendancesoftware.lessons.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
