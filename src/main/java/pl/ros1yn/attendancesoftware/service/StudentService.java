package pl.ros1yn.attendancesoftware.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;

import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Student> deleteStudentById(Integer id) {
        Optional<Student> foundedStudent = studentRepository.findById(id);

        if (foundedStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
