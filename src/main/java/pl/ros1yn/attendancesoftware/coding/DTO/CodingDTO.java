package pl.ros1yn.attendancesoftware.coding.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodingDTO {

    private Integer id;
    private Integer group;

    @JsonProperty("student")
    private StudentDTO studentDTO;

    @JsonProperty("lesson")
    private LessonSimpleDTO lessonSimpleDTO;

}
