package pl.ros1yn.attendancesoftware.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonRequest {

    private Integer id;
    private String title;
    private Integer semester;
    private Integer year;

}
