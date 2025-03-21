package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class CodingDeleteService {

    private final CodingRepository codingRepository;
    private final ClassFinder classFinder;

    public ResponseEntity<CodingResponse> deleteCoding(Integer id) {

        classFinder.findCoding(id);
        codingRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
