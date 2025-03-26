package pl.ros1yn.attendancesoftware.student.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class StudentDeleteService {

    private final StudentRepository studentRepository;
    private final ClassFinder classFinder;

    public ResponseEntity<Student> deleteStudent(Integer studentId) {

        classFinder.findStudent(studentId);
        studentRepository.deleteById(studentId);

        return ResponseEntity.noContent().build();
    }
}
