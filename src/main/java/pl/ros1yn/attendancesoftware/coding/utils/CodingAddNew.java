package pl.ros1yn.attendancesoftware.coding.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
public class CodingAddNew {

    public Coding getNewCoding(CodingRequestDTO requestDTO, Student foundedStudent, Lesson foundedLesson) {
        Coding coding = new Coding();
        coding.setStudent(foundedStudent);
        coding.setLesson(foundedLesson);
        coding.setGroup(requestDTO.getGroup());
        return coding;
    }
}
