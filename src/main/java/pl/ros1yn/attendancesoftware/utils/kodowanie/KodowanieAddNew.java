package pl.ros1yn.attendancesoftware.utils.kodowanie;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.DTO.KodowanieRequestDTO;
import pl.ros1yn.attendancesoftware.model.Kodowanie;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.model.Zajecia;

@Component
public class KodowanieAddNew {

    public Kodowanie getNewKodowanie(KodowanieRequestDTO requestDTO, Student foundedStudent, Zajecia foundedZajecia) {
        Kodowanie kodowanie = new Kodowanie();
        kodowanie.setStudent(foundedStudent);
        kodowanie.setZajecia(foundedZajecia);
        kodowanie.setGrupa(requestDTO.getGrupa());
        return kodowanie;
    }
}
