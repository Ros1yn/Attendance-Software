package pl.ros1yn.attendancesoftware.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;
import pl.ros1yn.attendancesoftware.service.ObecnoscService;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ObecnoscController {

    private final ObecnoscRepository obecnoscRepository;

    private final ObecnoscService obecnoscService;


    @GetMapping("obecnosci")
    public ResponseEntity<Iterable<Obecnosc>> getAllObecnosci() {
        return ResponseEntity.ok(obecnoscRepository.findAll());
    }

    @GetMapping("obecnosci/{id}")
    public ResponseEntity<Optional<Obecnosc>> getSingleObecnosc(@PathVariable Integer id) {
        return obecnoscService.getObecnosc(id);
    }

    @DeleteMapping("obecnosci/{id}")
    public ResponseEntity<Obecnosc> deleteObecnosc(@PathVariable Integer id) {
        return obecnoscService.deleteObecnoscById(id);
    }

    @PostMapping("obecnosci")
    public ResponseEntity<Obecnosc> addObecnosc(@RequestBody Obecnosc obecnosc) {

        return obecnoscService.getObecnoscResponseEntity(obecnosc);
    }

    @PatchMapping("obecnosci/{id}")
    public ResponseEntity<Obecnosc> updateFullObecnosc(@PathVariable Integer id, @RequestBody Obecnosc obecnosc) {

        return obecnoscService.updateObecnosc(id, obecnosc);

    }

    @PutMapping("obecnosci/{id}")
    public ResponseEntity<Obecnosc> updateObecnoscPartially(@PathVariable Integer id, @RequestBody Obecnosc obecnosc) {
        return obecnoscService.updatePartially(id, obecnosc);
    }


}

