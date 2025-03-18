package pl.ros1yn.attendancesoftware.coding.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.coding.dto.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.service.CodingService;

import java.util.List;

@RestController
@RequestMapping("coding")
@AllArgsConstructor
class CodingController {

    private final CodingService codingService;

    @GetMapping("/")
    ResponseEntity<List<CodingDTO>> getAllCodings() {
        return codingService.getAllCodings();
    }

    @GetMapping("/{id}")
    ResponseEntity<CodingDTO> getSingleCoding(@PathVariable Integer id) {
        return codingService.getSingleCoding(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CodingDTO> deleteCoding(@PathVariable Integer id) {
        return codingService.deleteCoding(id);
    }

    @PostMapping("/")
    ResponseEntity<CodingDTO> addCoding(@RequestBody CodingRequestDTO codingRequestDTO) {
        return codingService.addCoding(codingRequestDTO);
    }
}
