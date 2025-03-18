package pl.ros1yn.attendancesoftware.lessons.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.service.LessonDeleteService;
import pl.ros1yn.attendancesoftware.lessons.service.LessonGetService;
import pl.ros1yn.attendancesoftware.lessons.service.LessonUpdateService;

import java.util.List;

@RestController
@RequestMapping("lesson")
@AllArgsConstructor
class LessonController {

    private final LessonDeleteService lessonDeleteService;
    private final LessonGetService lessonGetService;
    private final LessonUpdateService lessonUpdateService;

    @GetMapping("/")
    ResponseEntity<List<LessonDTO>> getAllLessons() {
        return lessonGetService.getAllLessons();
    }

    @GetMapping("/{id}")
    ResponseEntity<LessonDTO> getSingleLesson(@PathVariable Integer id) {
        return lessonGetService.getSingleLesson(id);
    }

    //Do naprawy
    @DeleteMapping("/{id}")
    ResponseEntity<LessonDTO> deleteLesson(@PathVariable Integer id) {
        return lessonDeleteService.deleteLesson(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<Lesson> updateFullLesson(@PathVariable Integer id, @RequestBody LessonSimpleDTO lessonSimpleDTO) {
        return lessonUpdateService.fullUpdate(id, lessonSimpleDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Lesson> updateLessonPartially(@PathVariable Integer id, @RequestBody LessonSimpleDTO lessonSimpleDTO) {
        return lessonUpdateService.updatePartially(id, lessonSimpleDTO);
    }
}
