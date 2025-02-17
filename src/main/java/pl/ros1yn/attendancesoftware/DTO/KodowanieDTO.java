package pl.ros1yn.attendancesoftware.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KodowanieDTO {

    private Integer id;
    private Integer grupa;

    @JsonProperty("student")
    private StudentDTO studentDTO;

    @JsonProperty("zajecia")
    private ZajeciaDTO zajeciaDTO;

}
