package pl.ros1yn.attendancesoftware.lessons.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonDTO {

    private Integer id;
    private String title;
    private Integer semester;
    private Integer year;

    private List<Integer> codingId;

}
