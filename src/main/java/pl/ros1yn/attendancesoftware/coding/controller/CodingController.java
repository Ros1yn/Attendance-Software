package pl.ros1yn.attendancesoftware.coding.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.service.CodingGetService;
import pl.ros1yn.attendancesoftware.coding.service.CodingPostService;
import pl.ros1yn.attendancesoftware.coding.service.CodingDeleteService;
import pl.ros1yn.attendancesoftware.coding.service.CodingUpdateService;

import java.util.List;

@RestController
@RequestMapping("coding")
@AllArgsConstructor
@Slf4j
class CodingController {

    private final CodingDeleteService deleteService;
    private final CodingUpdateService updateService;
    private final CodingGetService getService;
    private final CodingPostService postService;

    @GetMapping("/")
    ResponseEntity<List<CodingResponse>> getAllCodings() {
        log.info("Recived request for getAllCodings");
        return getService.getAllCodings();
    }

    @GetMapping("/{codingId}")
    ResponseEntity<CodingResponse> getSingleCoding(@PathVariable Integer codingId) {
        log.info("Recived request for getSingleCoding with id: {}", codingId);
        return getService.getSingleCoding(codingId);
    }

    @DeleteMapping("/{codingId}")
    ResponseEntity<CodingResponse> deleteCoding(@PathVariable Integer codingId) {
        log.info("Recived request for deleteCoding with id: {}", codingId);
        return deleteService.deleteCoding(codingId);
    }

    @PostMapping("/")
    ResponseEntity<CodingResponse> addCoding(@RequestBody CodingRequestDTO codingRequestDTO) {
        log.info("Recived request for addCoding with body: {}", codingRequestDTO);
        return postService.addCoding(codingRequestDTO);
    }

    @PutMapping("/{codingId}")
    ResponseEntity<CodingResponse> updateCoding(@PathVariable Integer codingId, @RequestBody CodingRequestDTO requestDTO) {
        log.info("Recived request for updateCoding with id: {} - and body: {}", codingId, requestDTO);
        return updateService.updateCoding(codingId, requestDTO);
    }

    @PatchMapping("/{codingId}")
    ResponseEntity<CodingResponse> updateCodingPartially(@PathVariable Integer codingId, @RequestBody CodingRequestDTO requestDTO){
        log.info("Recived request for updateCodingPartially with id: {} - and body: {}", codingId, requestDTO);
        return updateService.updateCodingPartially(codingId, requestDTO);
    }
}
