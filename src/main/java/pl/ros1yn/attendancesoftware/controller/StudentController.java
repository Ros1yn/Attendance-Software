package pl.ros1yn.attendancesoftware.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.service.StudentService;

import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity<Iterable<Student>> getAllStudents() {

        return studentService.getAllStudentsFromDB();
    }

    @GetMapping("students/{indexNumber}")
    public ResponseEntity<Optional<Student>> getSingleStudent(@PathVariable Integer indexNumber) {

        return studentService.getSingleStudentFromDB(indexNumber);

    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudentById(id);
    }

    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        return studentService.addStudentToDB(student);

    }


}
