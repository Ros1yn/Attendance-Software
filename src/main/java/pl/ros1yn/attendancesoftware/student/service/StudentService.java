package pl.ros1yn.attendancesoftware.student.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.student.utils.StudentUpdate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentUpdate studentUpdate;

    public ResponseEntity<List<Student>> getAllStudents() {

        Iterable<Student> allStudents = studentRepository.findAll();

        List<Student> studentList = new ArrayList<>();

        for (Student student : allStudents) {
            studentList.add(student);
        }

        studentList.sort(Comparator.comparingInt(Student::getIndexNumber));

        return ResponseEntity.ok(studentList);
    }

    public ResponseEntity<Student> deleteStudent(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Student> addStudent(Student student) {

        Student savedStudent = studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    public ResponseEntity<Optional<Student>> getSingleStudent(Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalStudent);
    }

    public ResponseEntity<Student> updateFullStudent(Student student, Integer id) {

        return studentRepository.findById(id)
                .map(existingStudent -> studentUpdate.update(existingStudent, student))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
