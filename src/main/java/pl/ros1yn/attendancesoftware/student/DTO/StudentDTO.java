package pl.ros1yn.attendancesoftware.student.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class StudentDTO {

    private Integer indexNumber;
    private String name;
    private String surname;

}
