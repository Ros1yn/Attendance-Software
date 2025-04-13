package pl.ros1yn.attendancesoftware.student.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentRequestDTO {

    private String name;
    private String surname;

}
