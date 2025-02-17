package pl.ros1yn.attendancesoftware.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.model.Obecnosc;
import pl.ros1yn.attendancesoftware.repository.ObecnoscRepository;
import pl.ros1yn.attendancesoftware.service.ObecnoscService;

@RestController
@AllArgsConstructor
public class ObecnoscController {

    private ObecnoscRepository obecnoscRepository;

    private ObecnoscService obecnoscService;


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

