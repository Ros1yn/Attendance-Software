package pl.ros1yn.attendancesoftware.utils.obecnosc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;

@Component
@AllArgsConstructor
public class ObecnoscUpdate {

    private final ObecnoscRepository obecnoscRepository;

    public Obecnosc getNewObecnosc(Obecnosc obecnosc, Obecnosc existingObecnosc) {
        existingObecnosc.setStudent(obecnosc.getStudent());
        existingObecnosc.setCzyObecny(obecnosc.getCzyObecny());
        existingObecnosc.setAktywnosc(obecnosc.getAktywnosc());
        existingObecnosc.setListaObecnosci(obecnosc.getListaObecnosci());

        return obecnoscRepository.save(existingObecnosc);
    }

}
