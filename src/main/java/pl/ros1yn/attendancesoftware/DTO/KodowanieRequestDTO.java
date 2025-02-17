package pl.ros1yn.attendancesoftware.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KodowanieRequestDTO {

    private Integer indexNumber;
    private Integer grupa;
    private Integer zajeciaId;

}
