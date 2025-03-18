package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.mapper.CodingMapper;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.exception.CodingNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CodingService {

    private final CodingRepository codingRepository;
    private final CodingMapper codingMapper;
    private final ClassFinder classFinder;

    public ResponseEntity<List<CodingDTO>> getAllCodings() {

        Iterable<Coding> codings = codingRepository.findAll();
        List<CodingDTO> codingDTOList = new ArrayList<>();

        codings.forEach(coding -> codingDTOList.add(codingMapper.mapToDTO(coding)));

        return ResponseEntity.ok(codingDTOList);
    }

    public ResponseEntity<CodingDTO> getSingleCoding(Integer id) {
        return codingRepository.findById(id)
                .map(codingMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(CodingNotFoundException::new);
    }

    public ResponseEntity<CodingDTO> deleteCoding(Integer id) {

        classFinder.findCoding(id);
        codingRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<CodingDTO> addCoding(CodingRequestDTO requestDTO) {

        Student student = classFinder.findStudent(requestDTO.getIndexNumber());
        Lesson lesson = classFinder.findLesson(requestDTO.getLessonId());
        Coding coding = codingMapper.getNewCoding(requestDTO, student, lesson);
        Coding savedCoding = codingRepository.save(coding);

        return ResponseEntity.ok(codingMapper.mapToDTO(savedCoding));

    }
}
