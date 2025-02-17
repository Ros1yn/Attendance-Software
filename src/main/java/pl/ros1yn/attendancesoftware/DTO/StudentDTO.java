package pl.ros1yn.attendancesoftware.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class StudentDTO {

    private Integer indexNumber;
    private String imie;
    private String nazwisko;

}
