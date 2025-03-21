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

    public ResponseEntity<Student> deleteStudent(Integer id) {

        classFinder.findStudent(id);
        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
