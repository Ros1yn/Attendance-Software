package pl.ros1yn.attendancesoftware.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.utils.student.StudentUpdate;

import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    private final StudentUpdate studentUpdate;

    public StudentService(StudentRepository studentRepository, StudentUpdate studentUpdate) {
        this.studentRepository = studentRepository;
        this.studentUpdate = studentUpdate;
    }

    public ResponseEntity<Iterable<Student>> getAllStudentsFromDB() {

        Iterable<Student> allStudents = studentRepository.findAll();

        return ResponseEntity.ok(allStudents);
    }

    public ResponseEntity<Student> deleteStudentById(Integer id) {
        Optional<Student> foudnedStudent = studentRepository.findById(id);

        if (foudnedStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Student> addStudentToDB(Student student) {

        Student savedStudent = studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    public ResponseEntity<Optional<Student>> getSingleStudentFromDB(Integer id) {

        Optional<Student> foundedStudent = studentRepository.findById(id);

        if (foundedStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundedStudent);
    }

    public ResponseEntity<Student> updateFullStudent(Student student, Integer id) {

        return studentRepository.findById(id)
                .map(existingStudent -> studentUpdate.update(existingStudent, student))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}
