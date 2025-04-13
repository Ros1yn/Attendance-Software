package pl.ros1yn.attendancesoftware.student.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentResponse {

    private Integer indexNumber;
    private String name;
    private String surname;

}
