package pl.ros1yn.attendancesoftware.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;
import pl.ros1yn.attendancesoftware.utils.obecnosc.ObecnoscPartiallyUpdate;
import pl.ros1yn.attendancesoftware.utils.obecnosc.ObecnoscUpdate;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ObecnoscService {

    private final ObecnoscRepository obecnoscRepository;

    private final ObecnoscUpdate obecnoscUpdate;

    private final ObecnoscPartiallyUpdate obecnoscPartiallyUpdate;

    public ResponseEntity<Obecnosc> deleteObecnoscById(Integer id) {

        Optional<Obecnosc> foundedObecnosc = obecnoscRepository.findById(id);

        if (foundedObecnosc.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        obecnoscRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Obecnosc> getObecnoscResponseEntity(Obecnosc obecnosc) {
        Obecnosc savedObecnosc = obecnoscRepository.save(obecnosc);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedObecnosc);
    }

    public ResponseEntity<Optional<Obecnosc>> getObecnosc(int idListy) {

        Optional<Obecnosc> foundedObecnosc = obecnoscRepository.findById(idListy);

        if (foundedObecnosc.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundedObecnosc);
    }

    public ResponseEntity<Obecnosc> updateObecnosc(Integer id, Obecnosc obecnosc) {

        return obecnoscRepository.findById(id)
                .map(existingObecnosc -> obecnoscUpdate.getNewObecnosc(obecnosc, existingObecnosc))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Obecnosc> updatePartially(Integer id, Obecnosc obecnosc) {

        return obecnoscRepository.findById(id)
                .map(existingObecnosc -> obecnoscPartiallyUpdate.getUpdatedparially(obecnosc, existingObecnosc))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


}
