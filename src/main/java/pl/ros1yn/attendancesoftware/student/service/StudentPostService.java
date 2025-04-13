package pl.ros1yn.attendancesoftware.student.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Service
@AllArgsConstructor
@Slf4j
public class StudentPostService {

    private final StudentRepository studentRepository;

    public ResponseEntity<Student> addStudent(Student student) {

        Student savedStudent = studentRepository.save(student);

        log.info("Student has been added. Body: {}", savedStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
}
