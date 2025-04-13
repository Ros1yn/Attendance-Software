package pl.ros1yn.attendancesoftware.coding.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.student.dto.StudentResponse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodingResponse {

    private Integer id;
    private Integer group;

    @JsonProperty("student")
    private StudentResponse studentResponse;

    @JsonProperty("lesson")
    private LessonResponse lessonResponse;

}
