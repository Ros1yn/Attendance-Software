package pl.ros1yn.attendancesoftware.utils.kodowanie;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.DTO.KodowanieDTO;
import pl.ros1yn.attendancesoftware.DTO.StudentDTO;
import pl.ros1yn.attendancesoftware.DTO.ZajeciaDTO;
import pl.ros1yn.attendancesoftware.model.Kodowanie;

@Component
public class KodowanieToDTO {

    public KodowanieDTO convertToDTO(Kodowanie kodowanie) {

        StudentDTO studentDTO = new StudentDTO(
                kodowanie.getStudent().getIndexNumber(),
                kodowanie.getStudent().getImie(),
                kodowanie.getStudent().getNazwisko()
        );

        ZajeciaDTO zajeciaDTO = new ZajeciaDTO(
                kodowanie.getZajecia().getId(),
                kodowanie.getZajecia().getNazwa(),
                kodowanie.getZajecia().getSemestr(),
                kodowanie.getZajecia().getRok()
        );

        return new KodowanieDTO(
                kodowanie.getId(),
                kodowanie.getGrupa(),
                studentDTO,
                zajeciaDTO
        );
    }
}
