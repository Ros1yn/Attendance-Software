package pl.ros1yn.attendancesoftware.lessons.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.service.LessonService;

import java.util.List;

@RestController
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("lesson")
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("lesson/{id}")
    public ResponseEntity<LessonDTO> getSingleLesson(@PathVariable Integer id) {
        return lessonService.getSingleLesson(id);
    }

    //Do naprawy
    @DeleteMapping("lesson/{id}")
    public ResponseEntity<LessonDTO> deleteLesson(@PathVariable Integer id) {
        return lessonService.deleteLesson(id);
    }

    @PutMapping("lesson/{id}")
    public ResponseEntity<Lesson> updateFullLesson(@PathVariable Integer id, @RequestBody LessonSimpleDTO lessonSimpleDTO) {
        return lessonService.fullUpdate(id, lessonSimpleDTO);
    }

    @PatchMapping("lesson/{id}")
    public ResponseEntity<Lesson> updateLessonPartially(@PathVariable Integer id, @RequestBody LessonSimpleDTO lessonSimpleDTO) {
        return lessonService.updatePartially(id, lessonSimpleDTO);
    }
}
