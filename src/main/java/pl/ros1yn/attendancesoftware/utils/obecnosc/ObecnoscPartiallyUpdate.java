package pl.ros1yn.attendancesoftware.utils.obecnosc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;

@Component
@AllArgsConstructor
public class ObecnoscPartiallyUpdate {

    private final ObecnoscRepository obecnoscRepository;

    public Obecnosc getUpdatedparially(Obecnosc obecnosc, Obecnosc existingObecnosc) {
        if (obecnosc.getStudent() != null) existingObecnosc.setStudent(obecnosc.getStudent());
        if (obecnosc.getCzyObecny() != null) existingObecnosc.setCzyObecny(obecnosc.getCzyObecny());
        if (obecnosc.getAktywnosc() != 0) existingObecnosc.setAktywnosc(obecnosc.getAktywnosc());
        if (obecnosc.getListaObecnosci() != null) existingObecnosc.setListaObecnosci(obecnosc.getListaObecnosci());

        return obecnoscRepository.save(existingObecnosc);
    }

}
