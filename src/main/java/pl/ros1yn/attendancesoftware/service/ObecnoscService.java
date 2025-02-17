package pl.ros1yn.attendancesoftware.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;

import java.util.Optional;

@Component
public class ObecnoscService {

    private final ObecnoscRepository obecnoscRepository;

    public ObecnoscService(ObecnoscRepository obecnoscRepository) {
        this.obecnoscRepository = obecnoscRepository;
    }

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
}
