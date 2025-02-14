package pl.ros1yn.attendancesoftware.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.service.StudentService;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    private final StudentService studentService;

    public StudentController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity<Iterable<Student>> getAllStudents() {

        Iterable<Student> allStudents = studentRepository.findAll();

        return ResponseEntity.ok(allStudents);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudentById(id);
    }


}
