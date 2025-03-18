package pl.ros1yn.attendancesoftware.coding.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;
import pl.ros1yn.attendancesoftware.student.dto.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodingDTO {

    private Integer id;
    private Integer group;

    @JsonProperty("student")
    private StudentDTO studentDTO;

    @JsonProperty("lesson")
    private LessonDTO lessonDTO;

}
