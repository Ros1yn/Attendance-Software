package pl.ros1yn.attendancesoftware.coding.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodingRequestDTO {

    private Integer indexNumber;
    private Integer group;
    private Integer lessonId;

}
