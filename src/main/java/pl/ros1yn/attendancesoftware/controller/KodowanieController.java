package pl.ros1yn.attendancesoftware.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.DTO.KodowanieDTO;
import pl.ros1yn.attendancesoftware.DTO.KodowanieRequestDTO;
import pl.ros1yn.attendancesoftware.service.KodowanieService;

import java.util.List;

@RestController
@AllArgsConstructor
public class KodowanieController {

    private final KodowanieService kodowanieService;

    @GetMapping("kodowanie")
    public ResponseEntity<List<KodowanieDTO>> getAllKodowanie() {

        return kodowanieService.getAllKodowanie();
    }

    @GetMapping("kodowanie/{id}")
    public ResponseEntity<KodowanieDTO> getSingleKodowanie(@PathVariable Integer id) {

        return kodowanieService.getKodowanie(id);
    }

    @DeleteMapping("kodowanie/{id}")
    public ResponseEntity<KodowanieDTO> deleteKodowanie(@PathVariable Integer id) {
        return kodowanieService.deleteKodowanieById(id);
    }

    @PostMapping("kodowanie")
    public ResponseEntity<KodowanieDTO> addKodowanie(@RequestBody KodowanieRequestDTO kodowanieRequestDTO) {
        return kodowanieService.addNewKodowanie(kodowanieRequestDTO);
    }
}
