package pl.ros1yn.attendancesoftware.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ZajeciaDTO {

    private Integer id;
    private String nazwa;
    private Integer semestr;
    private Integer rok;

}
