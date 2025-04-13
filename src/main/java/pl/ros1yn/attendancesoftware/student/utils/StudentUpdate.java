package pl.ros1yn.attendancesoftware.student.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class StudentUpdate {

    private final StudentRepository studentRepository;

    public Student update(Student existingStudent, Student student) {

        existingStudent.setName(student.getName());
        existingStudent.setSurname(student.getSurname());

        return studentRepository.save(existingStudent);
    }
}
