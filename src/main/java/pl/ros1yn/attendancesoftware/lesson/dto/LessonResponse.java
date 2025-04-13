package pl.ros1yn.attendancesoftware.lesson.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonResponse {

    private Integer id;
    private String title;
    private Integer semester;
    private Integer year;

    private List<Integer> codingId;
}
