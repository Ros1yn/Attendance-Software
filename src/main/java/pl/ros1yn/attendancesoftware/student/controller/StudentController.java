package pl.ros1yn.attendancesoftware.student.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.student.dto.StudentRequestDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.service.StudentGetService;
import pl.ros1yn.attendancesoftware.student.service.StudentPostService;
import pl.ros1yn.attendancesoftware.student.service.StudentDeleteService;
import pl.ros1yn.attendancesoftware.student.service.StudentUpdateService;

import java.util.List;

@RestController
@RequestMapping("student")
@AllArgsConstructor
@Slf4j
class StudentController {

    private final StudentDeleteService deleteService;
    private final StudentGetService getService;
    private final StudentPostService postService;
    private final StudentUpdateService updateService;
    @GetMapping("/")
    ResponseEntity<List<Student>> getAllStudents() {
        log.info("Recived request for getAllStudents");
        return getService.getAllStudents();
    }

    @GetMapping("/{indexNumber}")
    ResponseEntity<Student> getSingleStudent(@PathVariable Integer indexNumber) {
        log.info("Recived request for getSingleStudent with indexNumber: {}", indexNumber);
        return getService.getSingleStudent(indexNumber);
    }

    @DeleteMapping("/{indexNumber}")
    ResponseEntity<Student> deleteStudent(@PathVariable Integer indexNumber) {
        log.info("Recived request for deleteLesson with id: {}", indexNumber);
        return deleteService.deleteStudent(indexNumber);
    }

    @PostMapping("/")
    ResponseEntity<Student> addStudent(@RequestBody Student student) {
        log.info("Recived request for addStudent with body: {}", student);
        return postService.addStudent(student);
    }

    @PutMapping("/{indexNumber}")
    ResponseEntity<Student> updateStudent(@PathVariable Integer indexNumber, @RequestBody StudentRequestDTO requestDTO) {
        log.info("Recived request for updateStudent with id: {} - and body: {}", indexNumber, requestDTO);
        return updateService.updateStudent(requestDTO, indexNumber);
    }

}
