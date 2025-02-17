package pl.ros1yn.attendancesoftware.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.service.StudentService;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("studenci")
    public ResponseEntity<Iterable<Student>> getAllStudents() {

        return studentService.getAllStudentsFromDB();
    }

    @GetMapping("studenci/{indexNumber}")
    public ResponseEntity<Optional<Student>> getSingleStudent(@PathVariable Integer indexNumber) {

        return studentService.getSingleStudentFromDB(indexNumber);

    }

    @DeleteMapping("studenci/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudentById(id);
    }

    @PostMapping("studenci")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        return studentService.addStudentToDB(student);

    }

    //Update imienia i nazwiska
    @PatchMapping("studenci/{indexNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer indexNumber, @RequestBody Student student) {
        return studentService.updateFullStudent(student, indexNumber);
    }

}
