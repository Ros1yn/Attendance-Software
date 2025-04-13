package pl.ros1yn.attendancesoftware.coding.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.service.CodingService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CodingController {

    private final CodingService codingService;

    @GetMapping("coding")
    public ResponseEntity<List<CodingDTO>> getAllCodings() {

        return codingService.getAllCodings();
    }

    @GetMapping("coding/{id}")
    public ResponseEntity<CodingDTO> getSingleCoding(@PathVariable Integer id) {

        return codingService.getSingleCoding(id);
    }

    @DeleteMapping("coding/{id}")
    public ResponseEntity<CodingDTO> deleteCoding(@PathVariable Integer id) {
        return codingService.deleteCoding(id);
    }

    @PostMapping("coding")
    public ResponseEntity<CodingDTO> addCoding(@RequestBody CodingRequestDTO kodowanieRequestDTO) {
        return codingService.addCoding(kodowanieRequestDTO);
    }
}
