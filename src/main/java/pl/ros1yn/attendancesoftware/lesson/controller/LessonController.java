package pl.ros1yn.attendancesoftware.lesson.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonRequest;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.service.LessonDeleteService;
import pl.ros1yn.attendancesoftware.lesson.service.LessonGetService;
import pl.ros1yn.attendancesoftware.lesson.service.LessonPostService;
import pl.ros1yn.attendancesoftware.lesson.service.LessonUpdateService;

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

    @GetMapping("/{lessonId}")
    ResponseEntity<LessonResponse> getSingleLesson(@PathVariable Integer lessonId) {
        log.info("Recived request for getSingleLesson with id: {}", lessonId);
        return getService.getSingleLesson(lessonId);
    }

    @DeleteMapping("/{lessonId}")
    ResponseEntity<LessonResponse> deleteLesson(@PathVariable Integer lessonId) {
        log.info("Recived request for deleteLesson with id: {}", lessonId);
        return deleteService.deleteLesson(lessonId);
    }

    @PostMapping("/")
    ResponseEntity<LessonResponse> addLesson(@RequestBody Lesson lesson){
        log.info("Recived request for addLesson with body: {}", lesson);
        return postService.addLesson(lesson);
    }

    @PutMapping("/{lessonId}")
    ResponseEntity<Lesson> updateFullLesson(@PathVariable Integer lessonId, @RequestBody LessonRequest simpleDTO) {
        log.info("Recived request for updateFullLesson with id: {} - and body: {}", lessonId, simpleDTO);
        return updateService.fullUpdate(lessonId, simpleDTO);
    }

    @PatchMapping("/{lessonId}")
    ResponseEntity<Lesson> updateLessonPartially(@PathVariable Integer lessonId, @RequestBody LessonRequest simpleDTO) {
        log.info("Recived request for updateLessonPartially with id: {} - and body: {}", lessonId, simpleDTO);
        return updateService.updatePartially(lessonId, simpleDTO);
    }
}
