package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.coding.utils.CodingFinder;

@Service
@AllArgsConstructor
@Slf4j
public class CodingDeleteService {

    private final CodingRepository codingRepository;
    private final CodingFinder codingFinder;

    public ResponseEntity<CodingResponse> deleteCoding(Integer codingId) {

        Coding coding = codingFinder.find(codingId);
        codingRepository.deleteById(codingId);

        log.info("Coding has been deleted. Body: {}", coding);
        return ResponseEntity.noContent().build();
    }
}
