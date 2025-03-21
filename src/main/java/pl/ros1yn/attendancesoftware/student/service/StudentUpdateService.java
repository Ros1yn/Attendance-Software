package pl.ros1yn.attendancesoftware.student.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.dto.StudentRequestDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.utils.StudentUpdate;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class StudentUpdateService {

    private final ClassFinder classFinder;
    private final StudentUpdate studentUpdate;

    @Transactional
    public ResponseEntity<Student> updateStudent(StudentRequestDTO newStudent, Integer indexNumber) {

        Student existingStudent = classFinder.findStudent(indexNumber);
        Student updatedStudent = studentUpdate.update(existingStudent, newStudent);

        return ResponseEntity.ok(updatedStudent);
    }
}
