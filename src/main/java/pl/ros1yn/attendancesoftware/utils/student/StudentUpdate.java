package pl.ros1yn.attendancesoftware.utils.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;

@Component
@AllArgsConstructor
public class StudentUpdate {

    private StudentRepository studentRepository;

    public Student update(Student existingStudent, Student student) {

        existingStudent.setImie(student.getImie());
        existingStudent.setNazwisko(student.getNazwisko());

        return studentRepository.save(existingStudent);
    }
}
