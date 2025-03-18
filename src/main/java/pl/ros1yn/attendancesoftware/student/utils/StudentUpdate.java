package pl.ros1yn.attendancesoftware.student.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
@AllArgsConstructor
public class StudentUpdate {

    public Student update(Student existingStudent, Student student) {

        existingStudent.setName(student.getName());
        existingStudent.setSurname(student.getSurname());

        return existingStudent;
    }
}
