package pl.ros1yn.attendancesoftware.student.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("student")
    public ResponseEntity<List<Student>> getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("student/{indexNumber}")
    public ResponseEntity<Optional<Student>> getSingleStudent(@PathVariable Integer indexNumber) {

        return studentService.getSingleStudent(indexNumber);

    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudent(id);
    }

    @PostMapping("student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);

    }

    //name and surname update only
    @PatchMapping("student/{indexNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer indexNumber, @RequestBody Student student) {
        return studentService.updateFullStudent(student, indexNumber);
    }

}
