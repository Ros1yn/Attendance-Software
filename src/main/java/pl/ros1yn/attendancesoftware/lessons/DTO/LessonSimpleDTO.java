package pl.ros1yn.attendancesoftware.lessons.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonSimpleDTO {

    private Integer id;
    private String title;
    private Integer semester;
    private Integer year;

}
