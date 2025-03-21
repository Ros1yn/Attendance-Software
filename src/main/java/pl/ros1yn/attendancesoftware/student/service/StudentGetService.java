package pl.ros1yn.attendancesoftware.student.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentGetService {

    private final StudentRepository studentRepository;
    private final ClassFinder classFinder;

    public ResponseEntity<List<Student>> getAllStudents() {

        Iterable<Student> allStudents = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();

        allStudents.forEach(studentList::add);
        studentList.sort(Comparator.comparingInt(Student::getIndexNumber));

        return ResponseEntity.ok(studentList);
    }

    public ResponseEntity<Student> getSingleStudent(Integer id) {

        Student student = classFinder.findStudent(id);
        return ResponseEntity.ok(student);
    }
}
