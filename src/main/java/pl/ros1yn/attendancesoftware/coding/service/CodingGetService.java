package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.mapper.CodingMapper;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.exception.CodingNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CodingGetService {

    private final CodingRepository codingRepository;
    private final CodingMapper codingMapper;

    public ResponseEntity<List<CodingResponse>> getAllCodings() {

        Iterable<Coding> codings = codingRepository.findAll();
        List<CodingResponse> codingResponseList = new ArrayList<>();

        codings.forEach(coding -> codingResponseList.add(codingMapper.mapToDTO(coding)));

        return ResponseEntity.ok(codingResponseList);
    }

    public ResponseEntity<CodingResponse> getSingleCoding(Integer codingId) {
        return codingRepository.findById(codingId)
                .map(codingMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(CodingNotFoundException::new);
    }

}
