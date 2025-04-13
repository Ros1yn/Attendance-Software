package pl.ros1yn.attendancesoftware.student.utils;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.student.dto.StudentRequestDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
@AllArgsConstructor
public class StudentUpdate {

    @Transactional
    public Student update(Student existingStudent, StudentRequestDTO requestDTO) {

        existingStudent.setName(requestDTO.getName());
        existingStudent.setSurname(requestDTO.getSurname());

        return existingStudent;
    }
}
