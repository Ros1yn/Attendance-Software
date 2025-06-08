package pl.ros1yn.attendancesoftware.lesson.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.utils.Finder;

@Component
@Slf4j
@AllArgsConstructor
public class LessonFinder implements Finder<Lesson, Integer> {

    private final LessonRepository lessonRepository;

    @Override
    public Lesson find(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() ->  {
                    log.error("Lesson was not found");
                    return new LessonNotFoundException();
                });
    }
}
