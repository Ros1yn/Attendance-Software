package pl.ros1yn.attendancesoftware.lessons.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.service.LessonDeleteService;
import pl.ros1yn.attendancesoftware.lessons.service.LessonGetService;
import pl.ros1yn.attendancesoftware.lessons.service.LessonPostService;
import pl.ros1yn.attendancesoftware.lessons.service.LessonUpdateService;

import java.util.List;

@RestController
@RequestMapping("lesson")
@AllArgsConstructor
@Slf4j
class LessonController {

    private final LessonDeleteService deleteService;
    private final LessonGetService getService;
    private final LessonUpdateService updateService;
    private final LessonPostService postService;
    @GetMapping("/")
    ResponseEntity<List<LessonResponse>> getAllLessons() {
        log.info("Recived request for getAllLessons");
        return getService.getAllLessons();
    }

    @GetMapping("/{id}")
    ResponseEntity<LessonResponse> getSingleLesson(@PathVariable Integer id) {
        log.info("Recived request for getSingleLesson with id: {}", id);
        return getService.getSingleLesson(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<LessonResponse> deleteLesson(@PathVariable Integer id) {
        log.info("Recived request for deleteLesson with id: {}", id);
        return deleteService.deleteLesson(id);
    }

    @PostMapping("/")
    ResponseEntity<LessonResponse> addLesson(Lesson lesson){
        log.info("Recived request for addLesson with body: {}", lesson);
        return postService.addLesson(lesson);
    }

    @PutMapping("/{id}")
    ResponseEntity<Lesson> updateFullLesson(@PathVariable Integer id, @RequestBody LessonSimpleDTO simpleDTO) {
        log.info("Recived request for updateFullLesson with id: {} - and body: {}", id, simpleDTO);
        return updateService.fullUpdate(id, simpleDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Lesson> updateLessonPartially(@PathVariable Integer id, @RequestBody LessonSimpleDTO simpleDTO) {
        log.info("Recived request for updateLessonPartially with id: {} - and body: {}", id, simpleDTO);
        return updateService.updatePartially(id, simpleDTO);
    }
}
