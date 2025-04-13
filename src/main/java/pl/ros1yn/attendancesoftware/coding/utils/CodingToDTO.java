package pl.ros1yn.attendancesoftware.coding.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@Component
public class CodingToDTO {

    public CodingDTO convertToDTO(Coding coding) {

        StudentDTO studentDTO = new StudentDTO(
                coding.getStudent().getIndexNumber(),
                coding.getStudent().getName(),
                coding.getStudent().getSurname()
        );

        LessonSimpleDTO lessonSimpleDTO = new LessonSimpleDTO(
                coding.getLesson().getId(),
                coding.getLesson().getTitle(),
                coding.getLesson().getSemester(),
                coding.getLesson().getYear()
        );

        return new CodingDTO(
                coding.getId(),
                coding.getGroup(),
                studentDTO,
                lessonSimpleDTO
        );
    }
}
