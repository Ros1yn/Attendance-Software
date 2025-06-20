package pl.ros1yn.attendancesoftware.student.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.student.utils.StudentFinder;

@Service
@AllArgsConstructor
@Slf4j
public class StudentDeleteService {

    private final StudentRepository studentRepository;
    private final StudentFinder studentFinder;


    public ResponseEntity<Student> deleteStudent(Integer studentId) {

        Student student = studentFinder.find(studentId);
        studentRepository.deleteById(studentId);

        log.info("Student has been deleted. Body: {}", student);
        return ResponseEntity.noContent().build();
    }
}
