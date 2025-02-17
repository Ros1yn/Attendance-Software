package pl.ros1yn.attendancesoftware.utils.student;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;

@Component
public class StudentUpdate {

    private final StudentRepository studentRepository;

    public StudentUpdate(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student update(Student existingStudent, Student student) {

        existingStudent.setImie(student.getImie());
        existingStudent.setNazwisko(student.getNazwisko());

        return studentRepository.save(existingStudent);
    }
}
