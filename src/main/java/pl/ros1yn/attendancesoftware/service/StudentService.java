package pl.ros1yn.attendancesoftware.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.utils.UriHelper;

import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    private final UriHelper uriHelper;

    public StudentService(StudentRepository studentRepository, UriHelper uriHelper) {
        this.studentRepository = studentRepository;
        this.uriHelper = uriHelper;
    }

    public ResponseEntity<Iterable<Student>> getAllStudentsFromDB() {

        Iterable<Student> allStudents = studentRepository.findAll();

        return ResponseEntity.ok(allStudents);
    }

    public ResponseEntity<Student> deleteStudentById(Integer id) {
        Optional<Student> foundedStudent = studentRepository.findById(id);

        if (foundedStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Student> addStudentToDB(Student student) {

        Student savedStudent = studentRepository.save(student);

        return ResponseEntity.created(uriHelper.getUri(savedStudent)).body(savedStudent);
    }

    public ResponseEntity<Optional<Student>> getSingleStudentFromDB(Integer id) {

        Optional<Student> findedStudent = studentRepository.findById(id);

        if (findedStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(findedStudent);
    }
}
