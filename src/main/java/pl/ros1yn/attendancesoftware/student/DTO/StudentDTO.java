package pl.ros1yn.attendancesoftware.student.DTO;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentDTO {

    private Integer indexNumber;
    private String name;
    private String surname;

}
