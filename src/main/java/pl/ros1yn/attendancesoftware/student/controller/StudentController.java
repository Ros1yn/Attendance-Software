package pl.ros1yn.attendancesoftware.student.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
class StudentController {

    private final StudentService studentService;


    @GetMapping("student")
    ResponseEntity<List<Student>> getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("student/{indexNumber}")
    ResponseEntity<Student> getSingleStudent(@PathVariable Integer indexNumber) {

        return studentService.getSingleStudent(indexNumber);

    }

    @DeleteMapping("student/{id}")
    ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudent(id);
    }

    @PostMapping("student")
    ResponseEntity<Student> addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);

    }

    @PutMapping("student/{indexNumber}")
    ResponseEntity<Student> updateStudent(@PathVariable Integer indexNumber, @RequestBody Student student) {
        return studentService.updateFullStudent(student, indexNumber);
    }

}
