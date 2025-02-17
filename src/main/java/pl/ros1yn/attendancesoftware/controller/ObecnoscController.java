package pl.ros1yn.attendancesoftware.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;
import pl.ros1yn.attendancesoftware.service.ObecnoscService;

@RestController
public class ObecnoscController {

    private final ObecnoscRepository obecnoscRepository;

    private final ObecnoscService obecnoscService;

    public ObecnoscController(ObecnoscRepository obecnoscRepository, ObecnoscService obecnoscService) {
        this.obecnoscRepository = obecnoscRepository;
        this.obecnoscService = obecnoscService;
    }

    @GetMapping("obecnosci")
    public ResponseEntity<Iterable<Obecnosc>> getAllObecnosci() {
        return ResponseEntity.ok(obecnoscRepository.findAll());
    }

    @DeleteMapping("obecnosci/{id}")
    public ResponseEntity<Obecnosc> deleteObecnosc(@PathVariable Integer id) {
        return obecnoscService.deleteObecnoscById(id);
    }

    @PostMapping("obecnosci")
    public ResponseEntity<Obecnosc> addObecnosc(@RequestBody Obecnosc obecnosc) {

        return obecnoscService.getObecnoscResponseEntity(obecnosc);
    }


}

